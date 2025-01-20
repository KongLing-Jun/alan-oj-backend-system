package com.thinking.common.core.domain.VO;

import lombok.Getter;
import lombok.Setter;

/**
 * @author : [Alan]
 * @version : [v1.0]
 * @className : LoginUserVO
 * @description : [登录用户信息视图类]
 * @createTime : [2025/1/20 17:35]
 * @updateUser : [KongLingJun]
 * @updateTime : [2025/1/20 17:35]
 * @updateRemark : [v1.0]
 */
@Getter
@Setter
public class LoginUserVO {

    private String nickName;  //昵称

    private String avatar;     //头像
}
