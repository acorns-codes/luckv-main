package com.luckv.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.luckv.demo.dto.Auction;
import com.luckv.demo.mapper.MainMapper;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class MainService {
	
	private final  MainMapper mainmapper;

	// 신규등록 경매
	public List<Auction> auctionNew(Auction auction) {
		return mainmapper.auctionNew(auction);
	}
	
	// 마감임박 경매
	public List<Auction> auctionDeadline(Auction auction) {
		return mainmapper.auctionDeadline(auction);
	}
}
