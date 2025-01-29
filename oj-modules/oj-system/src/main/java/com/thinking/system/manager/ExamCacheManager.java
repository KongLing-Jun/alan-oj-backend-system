package com.thinking.system.manager;

import com.thinking.common.core.constants.CacheConstants;
import com.thinking.common.redis.service.RedisService;
import com.thinking.system.domain.entity.Exam;
import org.springframework.stereotype.Component;

/**
 * @author : [Alan]
 * @version : [v1.0]
 * @className : ExamCacheManager
 * @description : [考试缓存管理器]
 * @createTime : [2025/1/23 23:34]
 * @updateUser : [KongLingJun]
 * @updateTime : [2025/1/23 23:34]
 * @updateRemark : [v1.0]
 */
@Component
public class ExamCacheManager {

    public final RedisService redisService;

    public ExamCacheManager(RedisService redisService) {
        this.redisService = redisService;
    }

    public void addCache(Exam exam) {
        redisService.leftPushForList(getExamListKey(), exam.getExamId());
    }

    public void deleteCache(Long examId) {
        redisService.removeForList(getExamListKey(), examId);
        redisService.deleteObject(getDetailKey(examId));
        redisService.deleteObject(getExamQuestionListKey(examId));
    }

    private String getExamQuestionListKey(Long examId) {
        return CacheConstants.EXAM_QUESTION_LIST + examId;
    }

    private String getDetailKey(Long examId) {
        return CacheConstants.EXAM_DETAIL + examId;
    }

    private String getExamListKey() {
        return CacheConstants.EXAM_UNFINISHED_LIST;
    }
}
