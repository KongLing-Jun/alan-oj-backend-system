package com.thinking.friend.domain.question.es;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.LocalDateTime;

/**
 * @author : [Alan]
 * @version : [v1.0]
 * @className : QuestionES
 * @description : [描述说明该类的功能]
 * @createTime : [2025/2/2 23:12]
 * @updateUser : [KongLingJun]
 * @updateTime : [2025/2/2 23:12]
 * @updateRemark : [描述说明本次修改内容]
 */
@Getter
@Setter
@Document(indexName = "idx_question")
public class QuestionES {

    @Id
    @Field(type = FieldType.Long)
    private Long questionId;

    @Field(type = FieldType.Text, analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
    private String title;

    @Field(type = FieldType.Byte)
    private Integer difficulty;

    @Field(type = FieldType.Long)
    private Long timeLimit;

    @Field(type = FieldType.Long)
    private Long spaceLimit;

    @Field(type = FieldType.Text, analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
    private String content;

    @Field(type = FieldType.Text)
    private String questionCase;

    @Field(type = FieldType.Text)
    private String mainFuc;

    @Field(type = FieldType.Text)
    private String defaultCode;

    @Field(type = FieldType.Date, format = DateFormat.date_hour_minute_second)
    private LocalDateTime createTime;
}
