package com.thinking.common.core.enums;

import lombok.Getter;

/**
 * 编程语言类型
 */
@Getter
public enum ProgramType {

    JAVA(0, "java语言"),

    PYTHON(1, "python语言"),

    CPP(2, "c++语言"),

    GOLANG(3, "go语言"),

    JAVASCRIPT(4, "javascript语言");

    private final Integer value;

    private final String desc;

    ProgramType(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }
}
