package com.luckv.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.luckv.demo.dao.AttendDao;
import com.luckv.demo.vo.Attend;
import com.luckv.demo.vo.Auction;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class AttendService {
	
	private final  AttendDao  attendDao;
	
	// 입찰 등록
	public boolean insertAttend(Attend attend) {
		return attendDao.insertAttend(attend);	        
	}
	
	
	// 구매 목록
	public List<Auction> attendList(Auction auction) {
        return attendDao.attendList(auction);
	}

	// 내 입찰 목록
	public List<Auction> attendMy(Auction auction) {
        return attendDao.attendMy(auction);
	}


}
