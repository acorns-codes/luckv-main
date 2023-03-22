package com.luckv.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luckv.demo.dto.Attend;
import com.luckv.demo.dto.Auction;
import com.luckv.demo.mapper.AttendMapper;
import com.luckv.demo.response.DefaultRes;
import com.luckv.demo.response.ResponseMessage;
import com.luckv.demo.response.StatusCode;
import com.luckv.demo.service.AttendService;
import com.luckv.demo.service.AuctionService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AttendController {

	public final Logger logger = LoggerFactory.getLogger(NoticeController.class);
	private final AuctionService auctionService;

	@RequestMapping("/insertAttend")	
	public ResponseEntity insertAttend(int ano) {
		
        
		Auction auction = auctionService.auctionDetail(ano);
    	
    	return auction != null? new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.READ_BOARD, auction), HttpStatus.OK)
		:  new ResponseEntity(DefaultRes.res(StatusCode.NO_CONTENT, ResponseMessage.NOT_READ_BOARD), HttpStatus.OK);
	}

	
}
