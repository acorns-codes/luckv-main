package com.luckv.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.luckv.demo.service.AuctionService;
import com.luckv.demo.service.UserService;

@Component
public class luckvScheduler {
	
	@Autowired 
	AuctionService auctionService;
	
	@Autowired 
	UserService userService;
	
//	@Scheduled(fixedRate=5000)
	public void statusIngUpdate() {
		boolean row = auctionService.statusIngUpdate();
	}
	
}
	