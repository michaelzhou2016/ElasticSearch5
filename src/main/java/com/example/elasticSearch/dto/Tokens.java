package com.example.elasticSearch.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * @Author zhouliliang
 * @Description:
 * @Date: Created in 2018/7/20 16:57
 */
@Getter
@Setter
@ToString
public class Tokens implements Serializable {
    private List<Token> tokens;
}
