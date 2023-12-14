package com.web.finalproject.dto;

import lombok.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class TradeForm {
    private String userId;
    private String title;
    private String address;
    private String content;
    private String username;
    private int price;
    private List<MultipartFile> files = new ArrayList<>();

    @Builder
    public TradeForm(String title, String address, int price, String content, String username) {
        this.title = title;
        this.address = address;
        this.price = price;
        this.content = content;
        this.username = username;
    }

}
