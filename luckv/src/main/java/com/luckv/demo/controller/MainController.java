package com.luckv.demo.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luckv.demo.dto.Auction;
import com.luckv.demo.response.DefaultRes;
import com.luckv.demo.response.ResponseMessage;
import com.luckv.demo.response.StatusCode;
import com.luckv.demo.service.MainService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class MainController {

	public final Logger logger = LoggerFactory.getLogger(NoticeController.class);
	private final MainService mainservice;
	
	// 신규등록 경매
	 @GetMapping("/auctionNew")
	    public ResponseEntity auctionNew(Auction auction) {
		 List<Auction> news = mainservice.auctionNew(auction);
		 try {
				return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.READ_BOARD, news), HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity(DefaultRes.res(StatusCode.NO_CONTENT, ResponseMessage.NOT_READ_BOARD), HttpStatus.OK);
				}
	    }
	 
	 // 마감임박 경매 
	 @GetMapping("/auctionDeadline")
	    public ResponseEntity auctionDeadline(Auction auction) {
		 List<Auction> deadline = mainservice.auctionDeadline(auction);
		 try {
				return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.READ_BOARD, deadline), HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity(DefaultRes.res(StatusCode.NO_CONTENT, ResponseMessage.NOT_READ_BOARD), HttpStatus.OK);
				}
	    }
	 
	 // 구독동영상
	 @GetMapping("/auctionSubs")
	    public ResponseEntity auctionSubs(Auction auction) {
		 List<Auction> subs = mainservice.auctionSubs(auction);
		 try {
				return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.READ_BOARD, subs), HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity(DefaultRes.res(StatusCode.NO_CONTENT, ResponseMessage.NOT_READ_BOARD), HttpStatus.OK);
				}
	    }
	 
	 // 무료동영상
	 @GetMapping("/auctionFree")
	    public ResponseEntity auctionFree(Auction auction) {
		 List<Auction> free = mainservice.auctionFree(auction);
		 try {
				return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.READ_BOARD, free), HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity(DefaultRes.res(StatusCode.NO_CONTENT, ResponseMessage.NOT_READ_BOARD), HttpStatus.OK);
				}
	    }
}
