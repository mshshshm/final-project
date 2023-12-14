package com.web.finalproject.mapper;

import com.web.finalproject.vo.ImageVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface ImageMapper {

    /**
     * 모든 이미지 저장
     * @param images - 이미지 리스트
     */
    void saveAll(List<ImageVO> images);

    /**
     *  모든 파일 찾기
     * @param no - trade 번호 (FK)
     * @return ImageList
     */
    List<ImageVO> findAll(int no);

    ImageVO findOne(int no);

    void delete(int no);
}
