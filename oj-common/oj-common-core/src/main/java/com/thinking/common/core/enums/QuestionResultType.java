package com.thinking.common.core.enums;

import lombok.Getter;

/**
 * 问题结果类型
 */
@Getter
public enum QuestionResultType {

    ERROR(0), //未通过

    PASS(1), //通过

    UN_SUBMIT(2),  //未提交

    IN_JUDGE(3); //  系统判题中

    private final Integer value;

    QuestionResultType(Integer value) {
        this.value = value;
    }
}
