package com.thinking.system.service;

import com.thinking.system.domain.dto.question.QuestionAddDTO;
import com.thinking.system.domain.dto.question.QuestionEditDTO;
import com.thinking.system.domain.dto.question.QuestionQueryDTO;
import com.thinking.system.domain.vo.question.QuestionDetailVO;

import java.util.List;

public interface QuestionService {

    List<?> list(QuestionQueryDTO questionQueryDTO);

    boolean add(QuestionAddDTO questionAddDTO);

    QuestionDetailVO detail(Long questionId);

    int edit(QuestionEditDTO questionEditDTO);

    int delete(Long questionId);
}
