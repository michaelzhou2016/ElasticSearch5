package com.example.elasticSearch.repository;

import com.example.elasticSearch.entity.ArticleEntity;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author zhouliliang
 * @Description:
 * @Date: Created in 2018/7/20 14:15
 */
@Repository
public interface ArticleRepository extends ElasticsearchRepository<ArticleEntity, Long> {

}
