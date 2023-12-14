package com.web.finalproject.service;

import com.web.finalproject.dto.TradeDetailResponseDto;
import com.web.finalproject.dto.TradeForm;
import com.web.finalproject.dto.TradeResponseDto;
import com.web.finalproject.dto.TradeUpdateDto;
import com.web.finalproject.vo.TradeVO;
import org.springframework.stereotype.Service;

import java.util.List;

public interface TradeService {
    public List<TradeResponseDto> findAll(String tradeTitle);

    int save(TradeForm tradeForm);

    TradeDetailResponseDto findById(int no);

    void update(int no, TradeUpdateDto tradeUpdateDto);

    void delete(int no);

    void updateStatus(int no, int status);

}
