package com.thinking.system.service;

import com.thinking.common.core.domain.Result;
import com.thinking.common.core.domain.VO.LoginUserVO;
import com.thinking.system.domain.dto.sysUser.UserSaveDTO;
import com.thinking.system.domain.entity.SysUser;
import com.thinking.system.domain.vo.user.SysUserVO;

public interface SysUserService {

    Result<String> login(String userAccount, String password);

    boolean logout(String token);

    Result<LoginUserVO> info(String token);

    int add(UserSaveDTO userSaveDTO);

    Result<Void> delete(Long userId);

    Result<SysUserVO> detail(Long userId, String sex);
}
