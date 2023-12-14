package com.web.finalproject.dao;

import com.web.finalproject.mapper.TradeMapper;
import com.web.finalproject.vo.TradeVO;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class TradeDaoTest {

    @Autowired
    TradeMapper tradeMapper;

    @Test
    void saveTest() {
        String userid = "get2";
        TradeVO tradeVO = TradeVO.builder()
                .userId(userid)
                .address("서울시 관악구")
                .price(5000)
                .title("good")
                .build();

        tradeMapper.save(tradeVO);


        TradeVO findTrade = tradeMapper.findById(tradeVO.getNo()).get();
        Assertions.assertThat(findTrade.getNo()).isEqualTo(tradeVO.getNo());

    }

    @Test
    void tradeStatusTest() {
        String userid = "hello";
        TradeVO tradeVO = TradeVO.builder()
                .userId(userid)
                .address("서울시 중구")
                .price(5000)
                .title("good")
                .content("good")
                .username("hello")
                .build();

        tradeMapper.save(tradeVO);

        TradeVO findTrade = tradeMapper.findById(tradeVO.getNo()).get();

        Assertions.assertThat(findTrade.getStatus()).isEqualTo(1);

    }

}