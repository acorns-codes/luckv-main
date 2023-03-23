package com.luckv.demo.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.luckv.demo.dto.Attend;
import com.luckv.demo.dto.Frequently;

@Mapper
@Repository
public interface AttendMapper {

		// 입찰등록
		boolean insertAttend(Attend Attend);
		
}
