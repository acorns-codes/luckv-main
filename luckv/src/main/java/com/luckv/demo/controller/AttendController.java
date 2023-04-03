package com.luckv.demo.controller;

import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
@Controller
public class AttendController {

	public final Logger logger = LoggerFactory.getLogger(NoticeController.class);
	private final AttendService  attendService;
	private final AuctionService auctionService;
//
//	@RequestMapping("/insertAttend")	
//	public ResponseEntity insertAttend(int ano) {
//		
//        
//		Auction auction = auctionService.auctionDetail(ano);
//    	
//    	return auction != null? new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.READ_BOARD, auction), HttpStatus.OK)
//		:  new ResponseEntity(DefaultRes.res(StatusCode.NO_CONTENT, ResponseMessage.NOT_READ_BOARD), HttpStatus.OK);
//	}
	// /receive를 메시지를 받을 endpoint로 설정합니다.
    @MessageMapping("/attend/{ano}")
    // /send로 메시지를 반환합니다.
    @SendTo("/send/{ano}")   
    // SocketHandler는 1) /receive에서 메시지를 받고, /send로 메시지를 보내줍니다.
    // 정의한 SocketVO를 1) 인자값, 2) 반환값으로 사용합니다.
    public Attend SocketHandler(@DestinationVariable int ano, Attend attend) {
        // 반환
    	System.out.println("확인=======" +  attend);
        return attend;
    }
    
    // room 입장시 send로 반환할 메세지
    @SubscribeMapping("/send/{ano}")
    	public Attend handleSubscripton(@DestinationVariable int ano) {
    	
    	Auction a = auctionService.auctionDetail(ano);
    	Attend attend = new Attend();
    	attend.setAno(ano);
    	attend.setBidding( a.getPayMax());
    	attend.setBuyer(a.getBuyer());
    	attend.setBuyerNm(a.getBuyerNm());
    	 	
        return attend;
       }
    	// 입찰 등록
	  @PostMapping("/insertAttend")
	    public ResponseEntity insertAuction(@RequestBody Attend attend) {
	        
	        boolean b = attendService.insertAttend(attend);
	        
	        if(b) {
	            return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.CREATED_BOARD, b), HttpStatus.OK);
	        }
	        return  new ResponseEntity(DefaultRes.res(StatusCode.BAD_REQUEST, ResponseMessage.NOT_CREATED_BOARD, b), HttpStatus.OK);
	    }
	    
	
		// 구매목록
	  @GetMapping("/attendList")
	    public ResponseEntity attendList(Auction auction) {		  	
		  	
	        // 페이지 설정
	        int sn = auction.getPage();   // 현재 페이지
	        int start = sn * 10 + 0; // 첫 페이지
	        int end = (sn + 1) * 10; // 끝 페이지

	        auction.setStart(start);
	        auction.setEnd(end);
	        
	        HashMap<String, Object>  attends = new HashMap<>();
	        attends.put("count", attendService.attendCount(auction));
		  	attends.put("auctionList", attendService.attendList(auction));

	        try {
				return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.READ_BOARD, attends), HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity(DefaultRes.res(StatusCode.NO_CONTENT, ResponseMessage.NOT_READ_BOARD), HttpStatus.OK);
				}
		 }
	  
	  	// 내 입찰목록
		@GetMapping("/attendMy")
		  public ResponseEntity attendMy(Auction auction) {		  	
			  	
	        // 페이지 설정
	        int sn = auction.getPage();   // 현재 페이지
	        int start = sn * 10 + 0; // 첫 페이지
	        int end = (sn + 1) * 10; // 끝 페이지

	        auction.setStart(start);
	        auction.setEnd(end);
	        
	        HashMap<String, Object>  attends = new HashMap<>();
	        attends.put("count", attendService.attendMyCount(auction));
		  	attends.put("auctionList", attendService.attendMy(auction));

	        try {
				return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.READ_BOARD, attends), HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity(DefaultRes.res(StatusCode.NO_CONTENT, ResponseMessage.NOT_READ_BOARD), HttpStatus.OK);
				}
		 }
	  
}
