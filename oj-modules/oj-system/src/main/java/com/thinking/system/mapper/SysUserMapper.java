package com.thinking.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.thinking.system.domain.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;

//系统用户接口
@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {
}