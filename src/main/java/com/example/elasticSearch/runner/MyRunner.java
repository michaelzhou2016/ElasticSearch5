package com.example.elasticSearch.runner;

import com.example.elasticSearch.dto.AnalyzeText;
import com.example.elasticSearch.dto.Token;
import com.example.elasticSearch.dto.Tokens;
import com.example.elasticSearch.entity.ArticleEntity;
import com.example.elasticSearch.service.ArticleService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author zhouliliang
 * @Description:
 * @Date: Created in 2018/7/20 14:21
 */
@Component
public class MyRunner implements CommandLineRunner {
    @Autowired
    ArticleService articleService;

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public void run(String... args) throws Exception {
//        List<ArticleEntity> articleEntities = Arrays.asList(new ArticleEntity(2L, "文章题目", "温岭", "温岭是个沿海城市", 1L),
//                new ArticleEntity(3L,
//                        "文章题目文章题目文章题目",
//                        "温1岭",
//                        "温2岭是个沿海城市",
//                        1L));
//        System.out.println(articleService.saveArticles(articleEntities));
        articleService.findArticles().forEach(System.out::println);

        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());


        AnalyzeText analyzeText = new AnalyzeText();
        analyzeText.setText("商品456，结实耐用，包邮，售后服务保障");
//        analyzeText.setAnalyzer("ik_max_word");

        HttpEntity<String> formEntity = new HttpEntity<String>(objectMapper.writeValueAsString(analyzeText), headers);

        String result = restTemplate.postForObject("http://localhost:9200/_analyze?pretty", formEntity, String.class);
        System.out.println(result);
        Tokens tokens = objectMapper.readValue(result, Tokens.class);
        System.out.println(tokens.toString());
        tokens.getTokens().stream().map(Token::getToken).collect(Collectors.toSet()).forEach(System.out::println);
    }
}
