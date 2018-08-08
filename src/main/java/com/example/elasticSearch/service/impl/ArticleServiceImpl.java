package com.example.elasticSearch.service.impl;

import com.example.elasticSearch.entity.ArticleEntity;
import com.example.elasticSearch.repository.ArticleRepository;
import com.example.elasticSearch.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author zhouliliang
 * @Description:
 * @Date: Created in 2018/7/20 14:18
 */
@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleRepository articleRepository;

    @Override
    public boolean saveArticles(List<ArticleEntity> articleBeanList) {
        articleRepository.saveAll(articleBeanList);
        return true;
    }

    @Override
    public Iterable<ArticleEntity> findArticles() {
        return articleRepository.findAll();
    }
}
