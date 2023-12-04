package com.web.finalproject.vo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TradeVO {
    private String userId;
    private int no;
    private int hit;
    private String subject;
    private int like;
    private LocalDateTime writedate;
}
