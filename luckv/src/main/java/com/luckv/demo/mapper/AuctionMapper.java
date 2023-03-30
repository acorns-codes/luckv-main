package com.luckv.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.luckv.demo.dto.Auction;

@Mapper
@Repository
public interface AuctionMapper {

	
		// 경매전체 리스트
		List<Auction> auctionAll(Auction auction);
		
		// 판매리스트 갯수
		int auctionCount(Auction auction);
		
		// 경매 등록
		int insertAuction(Auction auction);
		
		// 경매 상세보기
		Auction auctionDetail(int ano);
		
		// 경매->구독으로 변경 
		int auctionChange(Auction auction);
		
		// 경매 수정
		int auctionUpdate(Auction auction);
				
		// .무료 동영상 삭제		
		int auctionDelete(Auction auction);
		
		// status 변경
		int statusIngUpdate();
//		int statusEndUpdate();
}
