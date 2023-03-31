package com.luckv.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.luckv.demo.service.AuctionService;
import com.luckv.demo.service.UserService;

import lombok.extern.log4j.Log4j2;

@Component
//@Log4j2
public class luckvScheduler {
	
	@Autowired 
	AuctionService auctionService;
	
	@Autowired 
	UserService userService;
	
//	@Scheduled(fixedRate=1000)
//	public void statusIngUpdate() {
//		int row = auctionService.statusIngUpdate();
//	}
	
}
