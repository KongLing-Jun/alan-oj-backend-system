package com.thinking.system.domain.vo.es;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * @author : [Alan]
 * @version : [v1.0]
 * @className : QuestionES
 * @description : [es题库实体类]
 * @createTime : [2025/1/22 23:42]
 * @updateUser : [KongLingJun]
 * @updateTime : [2025/1/22 23:42]
 * @updateRemark : [v1.0]
 */
@Getter
@Setter
@Document(indexName = "idx_question") //用于标记这个类是一个Elasticsearch文档
public class QuestionES {

    @Id
    @Field(type = FieldType.Long)
    private Long questionId;
}
