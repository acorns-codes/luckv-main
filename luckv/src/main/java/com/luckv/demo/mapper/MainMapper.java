package com.luckv.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.luckv.demo.dto.Auction;

@Mapper
@Repository
public interface MainMapper {

		// 신규경매 목록
		List<Auction> auctionNew(Auction auction);
		
		// 마감임박경매 목록
		List<Auction> auctionDeadline(Auction auction);
		
		// 구독동영상 목록
		List<Auction> auctionSubs(Auction auction);
		
		// 무료동영상 목록
		List<Auction> auctionFree(Auction auction);
}
