package com.thinking.system.service.impl;

import com.github.pagehelper.PageHelper;
import com.thinking.common.core.enums.ResultCode;
import com.thinking.common.security.exception.ServiceException;
import com.thinking.system.domain.dto.user.UserDTO;
import com.thinking.system.domain.dto.user.UserQueryDTO;
import com.thinking.system.domain.entity.User;
import com.thinking.system.domain.vo.user.UserVO;
import com.thinking.system.manager.UserCacheManager;
import com.thinking.system.mapper.UserMapper;
import com.thinking.system.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : [Alan]
 * @version : [v1.0]
 * @className : UserServiceImpl
 * @description : [用户服务接口实现]
 * @createTime : [2025/1/23 23:24]
 * @updateUser : [KongLingJun]
 * @updateTime : [2025/1/23 23:24]
 * @updateRemark : [v1.0]
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    private final UserCacheManager userCacheManager;

    public UserServiceImpl(UserMapper userMapper, UserCacheManager userCacheManager) {
        this.userMapper = userMapper;
        this.userCacheManager = userCacheManager;
    }

    @Override
    public List<UserVO> list(UserQueryDTO userQueryDTO) {
        PageHelper.startPage(userQueryDTO.getPageNum(), userQueryDTO.getPageSize());
        return userMapper.selectUserList(userQueryDTO);
    }

    @Override
    public int updateStatus(UserDTO userDTO) {
        User user = userMapper.selectById(userDTO.getUserId());
        if (user == null) {
            throw new ServiceException(ResultCode.FAILED_USER_NOT_EXISTS);
        }
        user.setStatus(userDTO.getStatus());
        userCacheManager.updateStatus(user.getUserId(), user.getStatus());
        return userMapper.updateById(user);
    }
}
