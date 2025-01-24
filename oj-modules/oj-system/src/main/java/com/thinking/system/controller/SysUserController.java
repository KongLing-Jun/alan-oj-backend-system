package com.thinking.system.controller;

import com.thinking.common.core.controller.BaseController;
import com.thinking.common.core.domain.Result;
import com.thinking.system.domain.dto.sysUser.LoginDTO;
import com.thinking.system.domain.dto.sysUser.UserSaveDTO;
import com.thinking.system.domain.vo.user.SysUserVO;
import com.thinking.system.service.SysUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

/**
 * @author : [Alan]
 * @version : [v1.0]
 * @className : SysUserController
 * @description : [用户管理员控制层]
 * @createTime : [2025/1/23 23:20]
 * @updateUser : [KongLingJun]
 * @updateTime : [2025/1/23 23:20]
 * @updateRemark : [v1.0]
 */
@RestController
@RequestMapping("/sysUser")
@Tag(name = "用户管理员接口")
public class SysUserController extends BaseController {

    private final SysUserService sysUserService;

    public SysUserController(SysUserService sysUserService) {
        this.sysUserService = sysUserService;
    }

    //登录成功还是失败  bool true  false   int  code  1 成功   0 失败
    // 如果失败需要失败的原因     String  msg
    //请求方法  待定  和 url  sysuser/login
    //接口文档    统一的响应数据的结构
    //请求方法  post
    //请求参数  body

    //swagger 生成接口文档  第三方组件  公共的组件
    //接口地址：/system/sysUser/login

    @PostMapping("/login")
    @Operation(summary = "管理员登录", description = "根据账号密码进行管理员登录")
    @ApiResponse(responseCode = "1000", description = "操作成功")
    @ApiResponse(responseCode = "2000", description = "服务繁忙请稍后重试")
    @ApiResponse(responseCode = "3102", description = "用户不存在")
    @ApiResponse(responseCode = "3103", description = "用户名或密码错误")
    public Result<String> login(@RequestBody LoginDTO loginDTO) {
        return sysUserService.login(loginDTO.getUserAccount(),loginDTO.getPassword());
    }

    //管理员的增删改查
    //  接口地址： /sysUser/add
    //请求方法
    //新增
    //开发  测试   生成
    @PostMapping("/add")
    @Operation(summary = "新增管理员", description = "根据提供的信息新增管理员")
    @ApiResponse(responseCode = "1000", description = "操作成功")
    @ApiResponse(responseCode = "2000", description = "服务繁忙请稍后重试")
    @ApiResponse(responseCode = "3101", description = "用户已存在")
    public Result<Void> addSysUser(@RequestBody UserSaveDTO userSaveDTO) {
        return toResult(sysUserService.add(userSaveDTO));
    }

    @DeleteMapping("/{userId}")
    @Operation(summary = "删除用户", description = "通过用户id删除用户")
    @Parameters(value = {
            @Parameter(name = "userId", in = ParameterIn.PATH, description = "用户ID")
    })
    @ApiResponse(responseCode = "1000", description = "成功删除用户")
    @ApiResponse(responseCode = "2000", description = "服务繁忙请稍后重试")
    @ApiResponse(responseCode = "3101", description = "用户不存在")
    public Result<Void> delete(@PathVariable Long userId) {
        return sysUserService.delete(userId);
    }

    @Operation(summary = "用户详情", description = "根据查询条件查询用户详情")
    @GetMapping("/detail")
    @Parameters(value = {
            @Parameter(name = "userId", in = ParameterIn.QUERY, description = "用户ID"),
            @Parameter(name = "sex", in = ParameterIn.QUERY, description = "用户性别")
    })
    @ApiResponse(responseCode = "1000", description = "成功获取用户信息")
    @ApiResponse(responseCode = "2000", description = "服务繁忙请稍后重试")
    @ApiResponse(responseCode = "3101", description = "用户不存在")
    public Result<SysUserVO> detail(@RequestParam(required = true) Long userId,
                                    @RequestParam(required = false) String sex) {
        return sysUserService.detail(userId, sex);
    }
}
