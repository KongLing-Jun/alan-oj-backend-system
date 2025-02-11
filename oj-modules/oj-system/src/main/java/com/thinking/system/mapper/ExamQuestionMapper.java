package com.thinking.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.thinking.system.domain.entity.ExamQuestion;
import com.thinking.system.domain.vo.question.QuestionVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ExamQuestionMapper extends BaseMapper<ExamQuestion> {

    List<QuestionVO> selectExamQuestionList(Long examId);
}