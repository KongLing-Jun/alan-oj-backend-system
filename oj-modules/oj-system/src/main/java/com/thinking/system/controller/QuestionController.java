package com.thinking.system.controller;

import com.thinking.common.core.controller.BaseController;
import com.thinking.common.core.domain.Result;
import com.thinking.common.core.domain.TableDataInfo;
import com.thinking.system.domain.dto.question.QuestionAddDTO;
import com.thinking.system.domain.dto.question.QuestionEditDTO;
import com.thinking.system.domain.dto.question.QuestionQueryDTO;
import com.thinking.system.domain.vo.question.QuestionDetailVO;
import com.thinking.system.service.QuestionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

/**
 * @author : [Alan]
 * @version : [v1.0]
 * @className : QuestionController
 * @description : [题目控制层类]
 * @createTime : [2025/1/23 23:20]
 * @updateUser : [KongLingJun]
 * @updateTime : [2025/1/23 23:20]
 * @updateRemark : [v1.0]
 */
@RestController
@RequestMapping("/question")
@Tag(name = "题目管理接口")
public class QuestionController extends BaseController {

    private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping("/list")
    public TableDataInfo list(QuestionQueryDTO questionQueryDTO) {
        return getTableDataInfo(questionService.list(questionQueryDTO));
    }

    @PostMapping("/add")
    public Result<Void> add(@RequestBody QuestionAddDTO questionAddDTO) {
        return toResult(questionService.add(questionAddDTO));
    }

    @GetMapping("/detail")
    public Result<QuestionDetailVO> detail(Long questionId) {
        return Result.success(questionService.detail(questionId));
    }

    @PutMapping("/edit")
    public Result<Void> edit(@RequestBody QuestionEditDTO questionEditDTO) {
        return toResult(questionService.edit(questionEditDTO));
    }

    @DeleteMapping("/delete")
    public Result<Void> delete(Long questionId) {
        return toResult(questionService.delete(questionId));
    }
}
