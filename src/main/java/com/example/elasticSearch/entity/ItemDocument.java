package com.example.elasticSearch.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * @Author zhouliliang
 * @Description:
 * @Date: Created in 2018/8/8 14:20
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = ItemDocument.INDEX, type = ItemDocument.TYPE)
public class ItemDocument {

    public static final String INDEX = "items";
    public static final String TYPE = "item";

    /**
     * 商品唯一标识
     */
    @Id
    @Field(type = FieldType.Keyword)
    private String id;

    /**
     * 类目id
     */
    @Field(type = FieldType.Integer)
    private Integer catId;

    /**
     * 商品名称
     */
    @Field(type = FieldType.Text, index = false)
    private String name;


    /**
     * 商品价格
     */
    @Field(type = FieldType.Long)
    private Long price;


    /**
     * 商品的描述
     */
    @Field(type = FieldType.Text, searchAnalyzer = "ik_smart", analyzer = "ik_smart")
    private String description;
}
