package com.thinking.common.core.enums;

import lombok.Getter;

/**
 * 用户状态
 */
@Getter
public enum UserStatus {

    Block(0),

    Normal(1);

    private final Integer value;

    UserStatus(Integer value) {
        this.value = value;
    }
}
