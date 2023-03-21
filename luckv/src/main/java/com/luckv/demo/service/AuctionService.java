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
}
