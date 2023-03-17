package com.luckv.demo.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.luckv.demo.dto.Video;

@Mapper
@Repository
public interface VideoMapper {
	
	public int videoInsert(Video video) throws Exception;

}
