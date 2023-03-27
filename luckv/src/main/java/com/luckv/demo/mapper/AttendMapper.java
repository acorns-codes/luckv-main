package com.luckv.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.luckv.demo.dto.Attend;
import com.luckv.demo.dto.Auction;
import com.luckv.demo.dto.Frequently;

@Mapper
@Repository
public interface AttendMapper {

		// 입찰등록
		boolean insertAttend(Attend Attend);
		
		// 구매목록
		List<Auction> attendList(Auction auction);
}
