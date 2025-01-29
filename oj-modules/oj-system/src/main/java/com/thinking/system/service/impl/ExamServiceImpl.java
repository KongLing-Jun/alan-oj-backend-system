package com.thinking.system.service.impl;

import ch.qos.logback.core.joran.conditional.IfAction;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.thinking.common.core.constants.Constants;
import com.thinking.common.core.enums.ResultCode;
import com.thinking.common.security.exception.ServiceException;
import com.thinking.system.domain.dto.exam.ExamAddDTO;
import com.thinking.system.domain.dto.exam.ExamEditDTO;
import com.thinking.system.domain.dto.exam.ExamQueryDTO;
import com.thinking.system.domain.dto.exam.ExamQuestAddDTO;
import com.thinking.system.domain.entity.Exam;
import com.thinking.system.domain.entity.ExamQuestion;
import com.thinking.system.domain.entity.Question;
import com.thinking.system.domain.vo.exam.ExamDetailVO;
import com.thinking.system.domain.vo.exam.ExamVO;
import com.thinking.system.domain.vo.question.QuestionVO;
import com.thinking.system.manager.ExamCacheManager;
import com.thinking.system.mapper.ExamMapper;
import com.thinking.system.mapper.ExamQuestionMapper;
import com.thinking.system.mapper.QuestionMapper;
import com.thinking.system.service.ExamService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author : [Alan]
 * @version : [v1.0]
 * @className : QuestionServiceImpl
 * @description : [考试服务层实现类]
 * @createTime : [2025/1/23 23:24]
 * @updateUser : [KongLingJun]
 * @updateTime : [2025/1/23 23:24]
 * @updateRemark : [v1.0]
 */
@Service
public class ExamServiceImpl extends ServiceImpl<ExamQuestionMapper, ExamQuestion>
        implements ExamService
{
    private final ExamMapper examMapper;

    private final QuestionMapper questionMapper;

    private final ExamQuestionMapper examQuestionMapper;

    private final ExamCacheManager examCacheManager;

    public ExamServiceImpl(ExamMapper examMapper, QuestionMapper questionMapper, ExamQuestionMapper examQuestionMapper, ExamCacheManager examCacheManager) {
        this.examMapper = examMapper;
        this.questionMapper = questionMapper;
        this.examQuestionMapper = examQuestionMapper;
        this.examCacheManager = examCacheManager;
    }

    @Override
    public List<ExamVO> list(ExamQueryDTO examQueryDTO) {
        PageHelper.startPage(examQueryDTO.getPageNum(), examQueryDTO.getPageSize());
        return examMapper.selectExamList(examQueryDTO);
    }

    @Override
    public String add(ExamAddDTO examAddDTO) {
        checkExamSaveParams(examAddDTO, null);
        Exam exam = BeanUtil.copyProperties(examAddDTO, Exam.class);
        examMapper.insert(exam);
        return exam.getExamId().toString();
    }

    @Override
//    @Transactional
    public boolean questionAdd(ExamQuestAddDTO examQuestAddDTO) {
        Exam exam = getExam(examQuestAddDTO.getExamId());
        checkExam(exam);
        if (Constants.TRUE.equals(exam.getStatus())) {
            throw new ServiceException(ResultCode.EXAM_IS_PUBLISH);
        }
        Set<Long> questionIdSet = examQuestAddDTO.getQuestionIdSet();
        if (CollectionUtil.isNotEmpty(questionIdSet)) {
            return true;
        }
        List<Question> questionList = questionMapper.selectBatchIds(questionIdSet);
        if (CollectionUtil.isEmpty(questionList) || questionList.size() < questionIdSet.size()) {
            throw new ServiceException(ResultCode.EXAM_QUESTION_NOT_EXISTS);
        }
        return saveExamQuestion(exam, questionIdSet);
    }

    @Override
    public int questionDelete(Long examId, Long questionId) {
        Exam exam = getExam(examId);
        checkExam(exam);
        if (Constants.TRUE.equals(exam.getStatus())){
            throw new ServiceException(ResultCode.EXAM_IS_PUBLISH);
        }
        return examQuestionMapper.delete(new LambdaQueryWrapper<ExamQuestion>()
                .eq(ExamQuestion::getExamId,examId)
                .eq(ExamQuestion::getQuestionId,questionId));
    }

    @Override
    public ExamDetailVO detail(Long examId) {
        Exam exam = getExam(examId);
        ExamDetailVO examDetailVO = BeanUtil.copyProperties(exam, ExamDetailVO.class);
        List<QuestionVO> questionVOList = examQuestionMapper.selectExamQuestionList(examId);
        if (CollectionUtil.isEmpty(questionVOList)) {
            return examDetailVO;
        }
        examDetailVO.setExamQuestionList(questionVOList);
        return examDetailVO;
    }

    @Override
    public int edit(ExamEditDTO examEditDTO) {
        Exam exam = getExam(examEditDTO.getExamId());
        if (Constants.TRUE.equals(exam.getStatus())){
            throw new ServiceException(ResultCode.EXAM_IS_PUBLISH);
        }
        checkExam(exam);
        checkExamSaveParams(examEditDTO,examEditDTO.getExamId());
        exam.setTitle(examEditDTO.getTitle());
        exam.setStartTime(examEditDTO.getStartTime());
        exam.setEndTime(examEditDTO.getEndTime());
        return examMapper.updateById(exam);
    }

    @Override
    public int delete(Long examId) {
        Exam exam = getExam(examId);
        if (Constants.TRUE.equals(exam.getStatus())){
            throw new ServiceException(ResultCode.EXAM_IS_PUBLISH);
        }
        checkExam(exam);
        examQuestionMapper.delete(new LambdaQueryWrapper<ExamQuestion>()
                .eq(ExamQuestion::getExamId,examId));
        return examMapper.deleteById(exam);
    }

    @Override
    public int publish(Long examId) {
        Exam exam = getExam(examId);
        if (exam.getEndTime().isBefore(LocalDateTime.now())){
            throw new ServiceException(ResultCode.EXAM_IS_FINISH);
        }
        Long count = examQuestionMapper.selectCount(new LambdaQueryWrapper<ExamQuestion>()
                .eq(ExamQuestion::getExamId,examId));
        if (count == null || count <= 0) {
            throw new ServiceException(ResultCode.EXAM_NOT_HAS_QUESTION);
        }
        exam.setStatus(Constants.TRUE);
        //要将新发布的竞赛数据存储到redis   e:t:l  e:d:examId
        examCacheManager.addCache(exam);
        return examMapper.updateById(exam);
    }

    @Override
    public int cancelPublish(Long examId) {
        Exam exam = getExam(examId);
        checkExam(exam);
        if (exam.getEndTime().isBefore(LocalDateTime.now())){
            throw new ServiceException(ResultCode.EXAM_IS_PUBLISH);
        }
        exam.setStatus(Constants.FALSE);
        examCacheManager.deleteCache(examId);
        return examMapper.updateById(exam);
    }

    //参数合法性检验
    private void checkExamSaveParams(ExamAddDTO examAddDTO, Long examId) {
        //1、竞赛标题是否重复进行判断
        //2、竞赛开始、结束时间进行判断
        List<Exam> examList = examMapper
                .selectList(new LambdaQueryWrapper<Exam>()
                        .eq(Exam::getTitle, examAddDTO.getTitle())
                        .ne(examId != null, Exam::getExamId, examId));
        if(CollectionUtil.isNotEmpty(examList)) {
            throw new ServiceException(ResultCode.FAILED_ALREADY_EXISTS);
        }
        if (examAddDTO.getStartTime().isBefore(LocalDateTime.now())) {
            throw new ServiceException(ResultCode.EXAM_START_TIME_BEFORE_CURRENT_TIME);
        }
        if (examAddDTO.getStartTime().isAfter(examAddDTO.getEndTime())){
            throw new ServiceException(ResultCode.EXAM_START_TIME_AFTER_END_TIME);
        }
    }

    private Exam getExam(Long examId) {
        Exam exam = examMapper.selectById(examId);
        if (exam == null) {
            throw new ServiceException(ResultCode.FAILED_ALREADY_EXISTS);
        }
        return exam;
    }

    private void checkExam(Exam exam) {
        if (exam.getStartTime().isBefore(LocalDateTime.now())) {
            throw new ServiceException(ResultCode.EXAM_STARTED);
        }
    }

    //代码补全建议使用保证数据库操作事务一致性
//    @Transactional
    protected boolean saveExamQuestion(Exam exam, Set<Long> questionIdSet) {
        int num = 1;
        List<ExamQuestion> examQuestionList = new ArrayList<>();
        for (Long questionId : questionIdSet) {
            ExamQuestion examQuestion = new ExamQuestion();
            examQuestion.setExamId(exam.getExamId());
            examQuestion.setQuestionId(questionId);
            examQuestion.setQuestionOrder(num++);
            examQuestionList.add(examQuestion);
        }
        return saveBatch(examQuestionList);
    }
}
