package com.example.elasticSearch.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @Author zhouliliang
 * @Description:
 * @Date: Created in 2018/7/20 16:45
 */
@Getter
@Setter
public class AnalyzeText implements Serializable {
    private String analyzer = "ik_smart";
    private String text;
}
