package com.thinking.system.service.impl;

import com.thinking.common.core.domain.Result;
import com.thinking.common.core.domain.VO.LoginUserVO;
import com.thinking.common.core.enums.ResultCode;
import com.thinking.common.security.service.TokenService;
import com.thinking.system.domain.dto.sysUser.UserSaveDTO;
import com.thinking.system.domain.vo.user.SysUserVO;
import com.thinking.system.mapper.SysUserMapper;
import com.thinking.system.service.SysUserService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;

/**
 * @author : [Alan]
 * @version : [v1.0]
 * @className : SysUserServiceImpl
 * @description : [描述说明该类的功能]
 * @createTime : [2025/1/23 23:24]
 * @updateUser : [KongLingJun]
 * @updateTime : [2025/1/23 23:24]
 * @updateRemark : [描述说明本次修改内容]
 */
@Service
@RefreshScope
@Slf4j
public class SysUserServiceImpl implements SysUserService {

    @Resource
    private  SysUserMapper sysUserMapper;

    @Resource
    private  TokenService tokenService;

    @Value("${jwt.secret}")
    private String secret;


    @Override
    public Result<String> login(String userAccount, String password) {
        return null;
    }

    @Override
    public boolean logout(String token) {
        return false;
    }

    @Override
    public Result<LoginUserVO> info(String token) {
        return null;
    }

    @Override
    public int add(UserSaveDTO userSaveDTO) {
        return 0;
    }

    @Override
    public Result<Void> delete(Long userId) {
//        return sysUserMapper.delete(userId) > 0 ? Result.success() : Result.failed(ResultCode.FAILED);
        return null;
    }

    @Override
    public Result<SysUserVO> detail(Long userId, String sex) {
        return null;
    }
}
