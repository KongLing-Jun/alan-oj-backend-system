package com.thinking.common.core.enums;

import lombok.Getter;

/**
 * 用户身份枚举
 */
@Getter
public enum UserIdentity {

    ORDINARY(1, "普通用户"),

    ADMIN(2, "管理员");

    private final Integer value;

    private final String desc;

    UserIdentity(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }
}
