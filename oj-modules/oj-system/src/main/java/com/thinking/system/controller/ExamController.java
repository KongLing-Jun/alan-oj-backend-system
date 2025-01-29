package com.thinking.system.controller;

import com.thinking.common.core.controller.BaseController;
import com.thinking.common.core.domain.Result;
import com.thinking.common.core.domain.TableDataInfo;
import com.thinking.system.domain.dto.exam.ExamAddDTO;
import com.thinking.system.domain.dto.exam.ExamEditDTO;
import com.thinking.system.domain.dto.exam.ExamQueryDTO;
import com.thinking.system.domain.dto.exam.ExamQuestAddDTO;
import com.thinking.system.domain.vo.exam.ExamDetailVO;
import com.thinking.system.service.ExamService;
import org.springframework.web.bind.annotation.*;

/**
 * @author : [Alan]
 * @version : [v1.0]
 * @className : ExamController
 * @description : [考试控制层类]
 * @createTime : [2025/1/23 23:19]
 * @updateUser : [KongLingJun]
 * @updateTime : [2025/1/23 23:19]
 * @updateRemark : [v1.0]
 */
@RestController
@RequestMapping("/exam")
public class ExamController extends BaseController {

    private final ExamService examService;

    public ExamController(ExamService examService) {
        this.examService = examService;
    }

    @GetMapping("/list")
    public TableDataInfo list(ExamQueryDTO examQueryDTO) {
        return getTableDataInfo(examService.list(examQueryDTO));
    }

    @PostMapping("/add")
    public Result<String> add(@RequestBody ExamAddDTO examAddDTO) {
        return Result.success(examService.add(examAddDTO));
    }

    @PostMapping("/question/add")
    public Result<Void> questionAdd(@RequestBody ExamQuestAddDTO examQuestAddDTO) {
        return toResult(examService.questionAdd(examQuestAddDTO));
    }

    @DeleteMapping("/question/delete")
    public Result<Void> questionDelete(Long examId, Long questionId) {
        return toResult(examService.questionDelete(examId, questionId));
    }

    @GetMapping("/detail")
    public Result<ExamDetailVO> detail(Long examId) {
        return Result.success(examService.detail(examId));
    }

    @PutMapping("/edit")
    public Result<Void> edit(@RequestBody ExamEditDTO examEditDTO) {
        return toResult(examService.edit(examEditDTO));
    }

    @DeleteMapping("/delete")
    public Result<Void> delete(Long examId) {
        return toResult(examService.delete(examId));
    }

    //考试发布
    @PutMapping("/publish")
    public Result<Void> publish(Long examId) {
        return toResult(examService.publish(examId));
    }

    //取消考试发布
    @PutMapping("/cancelPublish")
    public Result<Void> cancelPublish(Long examId) {
        return toResult(examService.cancelPublish(examId));
    }
}
