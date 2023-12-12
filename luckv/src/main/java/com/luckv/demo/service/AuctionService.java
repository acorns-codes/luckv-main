package com.luckv.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.luckv.demo.dao.AuctionDao;
import com.luckv.demo.vo.Auction;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class AuctionService {
	
	private final AuctionDao auctionDao;
	
	// 경매전체 리스트
	public List<Auction> auctionList(Auction auction) {
        return auctionDao.auctionList(auction);
	}				

	// 판매 등록
	public boolean insertAuction(Auction auction) {		
        return auctionDao.insertAuction(auction);
	}
	
	// 판매 상세
	public Auction auctionDetail(int ano) {
        return auctionDao.auctionDetail(ano);
	}

	// 판매 수정
	public boolean auctionUpdate(Auction auction) {
		return auctionDao.auctionUpdate(auction);
	}
	
	// 경매->구독으로 변경 
	public boolean auctionChange(Auction auction) {
		return auctionDao.auctionChange(auction);
	}
			
	
	// 동영상 삭제
	public boolean auctionDelete(Auction auction) {
		return auctionDao.auctionDelete(auction);
    }
	
	// 판매상태변경
	public boolean statusIngUpdate() {
		return auctionDao.statusIngUpdate();
	}

	
}
