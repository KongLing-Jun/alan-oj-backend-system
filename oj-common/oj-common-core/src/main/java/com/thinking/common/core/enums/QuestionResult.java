package com.thinking.common.core.enums;

import lombok.Getter;

/**
 * 题目结果
 */
@Getter
public enum QuestionResult {

    ERROR(0),

    PASS(1);

    private final Integer value;

    QuestionResult(Integer value) {
        this.value = value;
    }
}
