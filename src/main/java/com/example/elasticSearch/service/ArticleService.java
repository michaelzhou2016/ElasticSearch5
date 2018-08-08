package com.example.elasticSearch.service;

import com.example.elasticSearch.entity.ArticleEntity;

import java.util.List;

/**
 * @Author zhouliliang
 * @Description:
 * @Date: Created in 2018/7/20 14:17
 */
public interface ArticleService {
    boolean saveArticles(List<ArticleEntity> articleBeanList);

    Iterable<ArticleEntity> findArticles();
}
