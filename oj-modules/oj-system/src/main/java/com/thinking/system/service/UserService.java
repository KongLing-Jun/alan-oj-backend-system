package com.thinking.system.service;

import com.thinking.system.domain.dto.user.UserDTO;
import com.thinking.system.domain.dto.user.UserQueryDTO;
import com.thinking.system.domain.vo.user.UserVO;

import java.util.List;

public interface UserService {

    List<UserVO> list(UserQueryDTO userQueryDTO);

    int updateStatus(UserDTO userDTO);

}
