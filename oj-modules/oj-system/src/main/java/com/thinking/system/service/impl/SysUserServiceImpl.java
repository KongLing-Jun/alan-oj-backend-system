package com.thinking.system.service.impl;

import ch.qos.logback.core.joran.conditional.IfAction;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.thinking.common.core.constants.Constants;
import com.thinking.common.core.constants.HttpConstants;
import com.thinking.common.core.domain.LoginUser;
import com.thinking.common.core.domain.Result;
import com.thinking.common.core.domain.VO.LoginUserVO;
import com.thinking.common.core.enums.ResultCode;
import com.thinking.common.core.enums.UserIdentity;
import com.thinking.common.security.exception.ServiceException;
import com.thinking.common.security.service.TokenService;
import com.thinking.system.domain.dto.sysUser.UserSaveDTO;
import com.thinking.system.domain.entity.SysUser;
import com.thinking.system.domain.vo.user.SysUserVO;
import com.thinking.system.mapper.SysUserMapper;
import com.thinking.system.service.SysUserService;
import com.thinking.system.utils.BCryptUtils;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;

import java.util.List;

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


    @Override //维护性、性能、安全
    public Result<String> login(String userAccount, String password) {
        //通过账号去数据库中查询，对应的用户信息
        LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<>();
        SysUser sysUser = sysUserMapper.selectOne(queryWrapper
                        .select(SysUser::getUserId, SysUser::getPassword, SysUser::getNickName)
                        .eq(SysUser::getUserAccount, userAccount));
        if (sysUser == null) {
            return Result.failed(ResultCode.FAILED_USER_NOT_EXISTS);
        }
        if (BCryptUtils.matchesPassword(password, sysUser.getPassword())) {
            //  jwttoken = 生产jwttoken的方法
            return Result.success(
                    tokenService.createToken(
                            sysUser.getUserId(),
                            secret,
                            UserIdentity.ADMIN.getValue(),
                            sysUser.getNickName(),
                            null));
        }
        return Result.failed(ResultCode.FAILED_LOGIN);
    }

    @Override
    public boolean logout(String token) {
        if (StrUtil.isNotEmpty(token) && token.startsWith(HttpConstants.PREFIX)) {
            token = token.replaceFirst(HttpConstants.PREFIX,StrUtil.EMPTY);
        }
        return tokenService.deleteLoginUser(token,secret);
    }

    @Override
    public Result<LoginUserVO> info(String token) {
        if (StrUtil.isNotEmpty(token) && token.startsWith(HttpConstants.PREFIX)) {
            token = token.replaceFirst(HttpConstants.PREFIX,StrUtil.EMPTY);
        }
        LoginUser loginUser = tokenService.getLoginUser(token,secret);
        if (loginUser == null) {
            return Result.failed();
        }
        LoginUserVO loginUserVO = new LoginUserVO();
        loginUserVO.setNickName(loginUser.getNickName());
        return Result.success(loginUserVO);
    }

    @Override
    public int add(UserSaveDTO userSaveDTO) {
        //将dto转为实体
        List<SysUser> sysUserList = sysUserMapper.selectList(new LambdaQueryWrapper<SysUser>()
                .eq(SysUser::getUserAccount, userSaveDTO.getUserAccount()));
        //用户已经存在
        if(CollectionUtil.isNotEmpty(sysUserList)) {
            throw new ServiceException(ResultCode.AILED_USER_EXISTS);
        }
        //属性赋值
        SysUser sysUser = new SysUser();
        sysUser.setUserAccount(userSaveDTO.getUserAccount());
        sysUser.setPassword(BCryptUtils.encryptPassword(userSaveDTO.getPassword()));
        sysUser.setCreateBy(Constants.SYSTEM_USER_ID);
        return sysUserMapper.insert(sysUser);
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
