package com.thinking.system.elasticsearch;

import com.thinking.system.domain.vo.es.QuestionES;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * @author : [Alan]
 * @version : [v1.0]
 * @className : QuestionRepository
 * @description : [es题库接口]
 * @createTime : [2025/1/22 23:51]
 * @updateUser : [KongLingJun]
 * @updateTime : [2025/1/22 23:51]
 * @updateRemark : [v1.0]
 */
@Repository
public interface QuestionRepository extends ElasticsearchRepository<QuestionES, Long> {
}
