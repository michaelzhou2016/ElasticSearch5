package com.example.elasticSearch.controller;

import com.example.elasticSearch.entity.ItemDocument;
import com.example.elasticSearch.repository.ItemDocumentRepository;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.elasticsearch.index.query.QueryBuilders.queryStringQuery;

/**
 * @Author zhouliliang
 * @Description:
 * @Date: Created in 2018/8/8 14:34
 */
@RestController
@RequestMapping("/items")
public class SearchController {

    @Autowired
    private ItemDocumentRepository repository;

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @GetMapping(path = "/{id}")
    public ResponseEntity getItem(@PathVariable("id") String id) {
        ItemDocument com = repository.findById(id).get();
        return new ResponseEntity(com.toString(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity createItem(@RequestBody ItemDocument document) {
        repository.save(document);

        return new ResponseEntity(document.toString(), HttpStatus.OK);
    }

    @GetMapping(path = "/singleWord")
    public List<ItemDocument> singleTitle(String word, @PageableDefault Pageable pageable) {
        //使用queryStringQuery完成单字符串查询
        SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(queryStringQuery(word)).withPageable(pageable).build();
        return elasticsearchTemplate.queryForList(searchQuery, ItemDocument.class);
    }

    @GetMapping(path = "/singleWord2")
    public Page<ItemDocument> singleTitle2(String word, @PageableDefault Pageable pageable) {
        //使用queryStringQuery完成单字符串查询
        QueryStringQueryBuilder builder = new QueryStringQueryBuilder(word).field("description");
        return repository.search(builder, pageable);
    }
}
