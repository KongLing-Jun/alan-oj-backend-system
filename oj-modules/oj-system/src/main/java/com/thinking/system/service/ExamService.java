package com.thinking.system.service;

import com.thinking.system.domain.dto.exam.ExamAddDTO;
import com.thinking.system.domain.dto.exam.ExamEditDTO;
import com.thinking.system.domain.dto.exam.ExamQueryDTO;
import com.thinking.system.domain.dto.exam.ExamQuestAddDTO;
import com.thinking.system.domain.vo.exam.ExamDetailVO;

import java.util.List;

public interface ExamService {
    
    List<?> list(ExamQueryDTO examQueryDTO);

    String add(ExamAddDTO examAddDTO);

    boolean questionAdd(ExamQuestAddDTO examQuestAddDTO);

    int questionDelete(Long examId, Long questionId);

    ExamDetailVO detail(Long examId);

    int edit(ExamEditDTO examEditDTO);

    int delete(Long examId);

    int publish(Long examId);

    int cancelPublish(Long examId);
}
