package com.thinking.system.mapper;

import com.thinking.system.domain.dto.user.UserQueryDTO;
import com.thinking.system.domain.vo.user.UserVO;

import java.util.List;

//查询用户列表接口
public interface UserMapper {

    List<UserVO> selectUserList(UserQueryDTO userQueryDTO);
}
