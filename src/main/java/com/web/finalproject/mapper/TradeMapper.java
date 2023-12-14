package com.web.finalproject.mapper;

import com.web.finalproject.dto.TradeUpdateDto;
import com.web.finalproject.vo.TradeVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface TradeMapper {

    void save(TradeVO tradeVO);

    void update(@Param("no") int no, @Param("updateParam") TradeUpdateDto tradeUpdateDto);

    Optional<TradeVO> findById(int id);
    
    // Search 추가
    List<TradeVO> findAll(String tradeTitle);

    void delete(int no);

    void updateStatus(int no, int status);
}
