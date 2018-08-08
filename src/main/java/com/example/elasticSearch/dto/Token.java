package com.example.elasticSearch.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @Author zhouliliang
 * @Description:
 * @Date: Created in 2018/7/20 16:55
 */
@Getter
@Setter
@ToString
public class Token implements Serializable {
    private String token;
    private String start_offset;
    private String end_offset;
    private String type;
    private String position;
}
