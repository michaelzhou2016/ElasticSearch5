package com.example.elasticSearch.repository;

import com.example.elasticSearch.entity.ItemDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @Author zhouliliang
 * @Description:
 * @Date: Created in 2018/8/8 14:26
 */
public interface ItemDocumentRepository extends ElasticsearchRepository<ItemDocument, String> {
}
