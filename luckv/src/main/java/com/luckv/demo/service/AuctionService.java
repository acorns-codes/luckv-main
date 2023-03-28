package com.luckv.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.luckv.demo.dto.Auction;
import com.luckv.demo.mapper.AuctionMapper;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class AuctionService {

	
		private final AuctionMapper auctionMapper;
		
		
		
		// 경매전체 리스트
		public List<Auction> auctionAll(Auction auction) {
	        return auctionMapper.auctionAll(auction);
		}				
				
		// 판매리스트 갯수
		public int auctionCount(Auction auction) {
			return auctionMapper.auctionCount(auction);
		}

		// 판매리스트 페이징처리
		public List<Auction> auctionPage(Auction auction) {
	        return auctionMapper.auctionPage(auction);
		}
		
		// 판매 등록
		public boolean insertAuction(Auction auction) {	
//			System.out.println("전================="+auction.getStartDay());
//			if(auction.getStartDay() != null) {
//				String startDay =  auction.getStartDay().replaceAll("T", " ");
//				auction.setStartDay(startDay);
//			}
//			if(auction. getLastDay() != null) {
//				String lastDay =  auction.getLastDay().replaceAll("T", " ");
//				auction.setLastDay(lastDay);
//			}
//			
//			System.out.println("후==================="+auction.getStartDay());
			int n = auctionMapper.insertAuction(auction);
	        return n > 0;
		}
		
		// 판매 상세
		public Auction auctionDetail(int qno) {
	        return auctionMapper.auctionDetail(qno);
		}

		// 판매 수정
		public boolean auctionUpdate(Auction auction) {
			int n = auctionMapper.auctionUpdate(auction);
	        return n > 0;
		}
		
		// 판매상태변경
		public int statusIngUpdate() {
			return auctionMapper.statusIngUpdate();
		}
		// 판매상태변경
//		public int statusEndUpdate() {
//			return auctionMapper.statusEndUpdate();
//		}
		
		// 동영상 삭제
		public boolean auctionDelete(int qno) {
			int n = auctionMapper.auctionDelete(qno);
	        return n > 0;
	    }
}
