package com.example.elasticSearch.entity;

/**
 * @Author zhouliliang
 * @Description:
 * @Date: Created in 2018/7/20 14:12
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;

/**
 * 文章
 */
@Document(indexName = "elasticsearch", type = "article")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleEntity implements Serializable {
    // 文章信息
    @Id
    private Long id;

    private String title;

    // 作者信息
    private String writer;

    private String content;

    // 归属信息
    private Long admin;
}
