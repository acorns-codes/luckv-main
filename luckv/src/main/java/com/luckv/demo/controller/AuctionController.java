package com.luckv.demo.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.luckv.demo.dto.Auction;
import com.luckv.demo.dto.Notice;
import com.luckv.demo.response.DefaultRes;
import com.luckv.demo.response.ResponseMessage;
import com.luckv.demo.response.StatusCode;
import com.luckv.demo.service.AuctionService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AuctionController {
	
		public final Logger logger = LoggerFactory.getLogger(NoticeController.class);
		private final AuctionService auctionService;
		
		
		
		// 경매 전체리스트
		  @GetMapping("/auctionAll")
		    public ResponseEntity<List<Auction>> auctionAll(Auction auction) {

		        // 페이지 설정
		        int sn = auction.getPage();   // 현재 페이지
		        int start = sn * 10 + 0; // 첫 페이지
		        int end = (sn + 1) * 10; // 끝 페이지

		        auction.setStart(start);
		        auction.setEnd(end);
		        
		        List<Auction> auctions = auctionService.auctionAll(auction);
				 try {
					return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.READ_BOARD, auctions), HttpStatus.OK);
					} catch (Exception e) {
						return new ResponseEntity(DefaultRes.res(StatusCode.NO_CONTENT, ResponseMessage.NOT_READ_BOARD), HttpStatus.OK);
					}
		    }
		  
		  
		// 판매 리스트 갯수
		 @GetMapping("/auctionCount")
		    public int auctionCount(Auction auction) {
		        return auctionService.auctionCount(auction);
		    } 
		 
		// 판매 리스트 페이징처리
		  @GetMapping("/auctionPage")
		    public List<Auction> auctionPage(Auction auction) {

		        // 페이지 설정
		        int sn = auction.getPage();   // 현재 페이지
		        int start = sn * 10 + 0; // 첫 페이지
		        int end = (sn + 1) * 10; // 끝 페이지

		        auction.setStart(start);
		        auction.setEnd(end);

		        return auctionService.auctionPage(auction);
		    }
		  

		   // 판매 등록
		  @PostMapping("/insertAuction")
		    public ResponseEntity insertAuction(@RequestBody Auction auction) {
		        
		        boolean b = auctionService.insertAuction(auction);
		        
		        if(b) {
		            return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.CREATED_BOARD, b), HttpStatus.OK);
		        }
		        return  new ResponseEntity(DefaultRes.res(StatusCode.BAD_REQUEST, ResponseMessage.NOT_CREATED_BOARD, b), HttpStatus.OK);
		    }
		    
		    // 판매 상세
		    @GetMapping("/auctionDetail")
		    public ResponseEntity auctionDetail(int ano) {
		    	Auction auction = auctionService.auctionDetail(ano);
		    	
		    	return auction != null? new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.READ_BOARD, auction), HttpStatus.OK)
				:  new ResponseEntity(DefaultRes.res(StatusCode.NO_CONTENT, ResponseMessage.NOT_READ_BOARD), HttpStatus.OK);
		    }
		    
		    // 판매 수정
		    @PostMapping("auctionUpdate")
		    public ResponseEntity auctionUpdate(@RequestBody Auction auction) {  
		    	boolean b = auctionService.auctionUpdate(auction);

				  if(b) {
			            return  new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.UPDATE_BOARD, b), HttpStatus.OK);
			        }
			        return  new ResponseEntity(DefaultRes.res(StatusCode.BAD_REQUEST, ResponseMessage.NOT_UPDATE_BOARD, b), HttpStatus.OK);
		    }


}
