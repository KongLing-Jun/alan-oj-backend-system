package com.thinking.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.thinking.system.domain.dto.question.QuestionQueryDTO;
import com.thinking.system.domain.entity.Question;
import com.thinking.system.domain.vo.question.QuestionVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface QuestionMapper extends BaseMapper<Question> {

    List<QuestionVO> selectQuestionList(QuestionQueryDTO questionQueryDTO);
}
