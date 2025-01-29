package com.thinking.system.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.pagehelper.PageHelper;
import com.thinking.common.core.constants.Constants;
import com.thinking.common.core.enums.ResultCode;
import com.thinking.common.security.exception.ServiceException;
import com.thinking.system.domain.dto.question.QuestionAddDTO;
import com.thinking.system.domain.dto.question.QuestionEditDTO;
import com.thinking.system.domain.dto.question.QuestionQueryDTO;
import com.thinking.system.domain.entity.Question;
import com.thinking.system.domain.vo.es.QuestionES;
import com.thinking.system.domain.vo.question.QuestionDetailVO;
import com.thinking.system.domain.vo.question.QuestionVO;
import com.thinking.system.elasticsearch.QuestionRepository;
import com.thinking.system.manager.QuestionCacheManager;
import com.thinking.system.mapper.QuestionMapper;
import com.thinking.system.service.QuestionService;
import org.mybatis.logging.Logger;
import org.mybatis.logging.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author : [Alan]
 * @version : [v1.0]
 * @className : QuestionServiceImpl
 * @description : [题目服务实现类]
 * @createTime : [2025/1/23 23:24]
 * @updateUser : [KongLingJun]
 * @updateTime : [2025/1/23 23:24]
 * @updateRemark : [v1.0]
 */
@Service
public class QuestionServiceImpl implements QuestionService {

    private final QuestionMapper questionMapper;

    private final QuestionRepository questionRepository;

    private final QuestionCacheManager questionCacheManager;

    public QuestionServiceImpl(QuestionMapper questionMapper,
                               QuestionRepository questionRepository,
                               QuestionCacheManager questionCacheManager)
    {
        this.questionMapper = questionMapper;
        this.questionRepository = questionRepository;
        this.questionCacheManager = questionCacheManager;
    }

    @Override
    public List<?> list(QuestionQueryDTO questionQueryDTO) {
        String excludeIdStr = questionQueryDTO.getExcludeIdStr();
        if(StrUtil.isNotEmpty(excludeIdStr)) {
            String[] excludeIdArr = excludeIdStr.split(Constants.SPLIT_SEM);
            Set<Long> excludeIdSet = Arrays.stream(excludeIdArr)
                    .map(Long::valueOf)
                    .collect(Collectors.toSet());
            questionQueryDTO.setExcludeIdSet(excludeIdSet);
        }
        PageHelper.startPage(questionQueryDTO.getPageNum(), questionQueryDTO.getPageSize());
        return questionMapper.selectQuestionList(questionQueryDTO);
    }

    @Override
    public boolean add(QuestionAddDTO questionAddDTO) {
        List<Question> questionList = questionMapper.selectList(new LambdaQueryWrapper<Question>()
                .eq(Question::getTitle, questionAddDTO.getTitle()));
        if(CollectionUtil.isNotEmpty(questionList)) {
            throw new ServiceException(ResultCode.FAILED_ALREADY_EXISTS);
        }
        //BeanUtil属性赋值器，MapStruct也可以使用
        Question question = BeanUtil.copyProperties(questionAddDTO, Question.class);
        int insertList = questionMapper.insert(question);
        if(insertList <= 0) {
            return false;
        }
        QuestionES questionES = BeanUtil.copyProperties(question, QuestionES.class);
        questionRepository.save(questionES);
        questionCacheManager.addCache(question.getQuestionId());
        return true;
    }

    @Override
    public QuestionDetailVO detail(Long questionId) {
        Question question = questionMapper.selectById(questionId);
        if(question == null) {
            throw new ServiceException(ResultCode.FAILED_NOT_EXISTS);
        }
        return BeanUtil.copyProperties(question, QuestionDetailVO.class);
    }

    @Override
    public int edit(QuestionEditDTO questionEditDTO) {
        Question oldQuestion = questionMapper.selectById(questionEditDTO.getQuestionId());
        if(oldQuestion == null) {
            throw new ServiceException(ResultCode.FAILED_NOT_EXISTS);
        }
        oldQuestion.setTitle(questionEditDTO.getTitle());
        oldQuestion.setDifficulty(questionEditDTO.getDifficulty());
        oldQuestion.setTimeLimit(questionEditDTO.getTimeLimit());
        oldQuestion.setSpaceLimit(questionEditDTO.getSpaceLimit());
        oldQuestion.setContent(questionEditDTO.getContent());
        oldQuestion.setQuestionCase(questionEditDTO.getQuestionCase());
        oldQuestion.setDefaultCode(questionEditDTO.getDefaultCode());
        oldQuestion.setMainFuc(questionEditDTO.getMainFuc());
        QuestionES questionES = BeanUtil.copyProperties(oldQuestion, QuestionES.class);
        questionRepository.save(questionES);
        return questionMapper.updateById(oldQuestion);
    }

    @Override
    public int delete(Long questionId) {
        Question question = questionMapper.selectById(questionId);
        if(question == null) {
            throw new ServiceException(ResultCode.FAILED_USER_NOT_EXISTS);
        }
        questionRepository.deleteById(questionId);
        questionCacheManager.deleteCache(questionId);
        return questionMapper.deleteById(questionId);
    }
}
