package com.thinking.system.controller;

import com.thinking.common.core.controller.BaseController;
import com.thinking.common.core.domain.Result;
import com.thinking.common.core.domain.TableDataInfo;
import com.thinking.system.domain.dto.user.UserDTO;
import com.thinking.system.domain.dto.user.UserQueryDTO;
import com.thinking.system.service.UserService;
import org.springframework.web.bind.annotation.*;

/**
 * @author : [Alan]
 * @version : [v1.0]
 * @className : UserController
 * @description : [用户控制层类]
 * @createTime : [2025/1/23 23:20]
 * @updateUser : [KongLingJun]
 * @updateTime : [2025/1/23 23:20]
 * @updateRemark : [v1.0]
 */
@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    //用户列表查询接口
    @RequestMapping("/list")
    public TableDataInfo list(UserQueryDTO userQueryDTO) {
        return getTableDataInfo(userService.list(userQueryDTO));
    }

    //用户状态修改接口,更新数据库中用户的状态信息。
    //todo 拉黑：限制用户操作   解禁：放开对于用户限制
    @PutMapping("/updateStatus")
    public Result<Void> updateStatus(@RequestBody UserDTO userDTO) {
        return toResult(userService.updateStatus(userDTO));
    }
}
