package com.example.elasticSearch;

import org.elasticsearch.action.admin.indices.analyze.AnalyzeAction;
import org.elasticsearch.action.admin.indices.analyze.AnalyzeRequestBuilder;
import org.elasticsearch.action.admin.indices.analyze.AnalyzeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author zhouliliang
 * @Description:
 * @Date: Created in 2018/7/20 13:36
 */
public class IK {
    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Autowired
    private static RestTemplate restTemplate;

    protected List<String> getIkAnalyzeSearchTerms(String searchContent) {
        AnalyzeRequestBuilder ikRequest = new AnalyzeRequestBuilder(elasticsearchTemplate.getClient(),
                AnalyzeAction.INSTANCE, SearchConstant.INDEX_NAME, searchContent);
        ikRequest.setTokenizer(SearchConstant.TOKENIZER_IK_MAX);
        List<AnalyzeResponse.AnalyzeToken> ikTokenList = ikRequest.execute().actionGet().getTokens();

        // 循环赋值
        List<String> searchTermList = new ArrayList<>();
        ikTokenList.forEach(ikToken -> {
            searchTermList.add(ikToken.getTerm());
        });

        return handlingIkResultTerms(searchTermList);
    }

    /**
     * 如果分词结果：洗发水（洗发、发水、洗、发、水）
     * - 均为词，保留
     * - 词 + 字，只保留词
     * - 均为字，保留字
     */
    private List<String> handlingIkResultTerms(List<String> searchTermList) {
        Boolean isPhrase = false;
        Boolean isWord = false;
        for (String term : searchTermList) {
            if (term.length() > SearchConstant.SEARCH_TERM_LENGTH) {
                isPhrase = true;
            } else {
                isWord = true;
            }
        }

        if (isWord & isPhrase) {
            List<String> phraseList = new ArrayList<>();
            searchTermList.forEach(term -> {
                if (term.length() > SearchConstant.SEARCH_TERM_LENGTH) {
                    phraseList.add(term);
                }
            });
            return phraseList;
        }

        return searchTermList;
    }

    public static void main(String[] args) {
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());

        HttpEntity<String> formEntity = new HttpEntity<String>("第二更新", headers);

        String result = restTemplate.postForObject("http://localhost:9200/_analyze?pretty&analyzer=ik", formEntity, String.class);
        System.out.println(result);
    }
}
