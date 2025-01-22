package com.thinking.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.thinking.system.domain.dto.exam.ExamQueryDTO;
import com.thinking.system.domain.entity.Exam;
import com.thinking.system.domain.vo.exam.ExamVO;

import java.util.List;

public interface ExamMapper extends BaseMapper<Exam> {

    List<ExamVO> selectExamList(ExamQueryDTO examQueryDTO);

}
