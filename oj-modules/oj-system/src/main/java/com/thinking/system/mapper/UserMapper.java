package com.thinking.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.thinking.system.domain.dto.user.UserQueryDTO;
import com.thinking.system.domain.entity.User;
import com.thinking.system.domain.vo.user.UserVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

//查询用户列表接口
@Mapper
public interface UserMapper extends BaseMapper<User> {

    List<UserVO> selectUserList(UserQueryDTO userQueryDTO);

}
