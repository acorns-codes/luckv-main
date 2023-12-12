package com.luckv.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.luckv.demo.service.AttendService;
import com.luckv.demo.service.AuctionService;
import com.luckv.demo.vo.Attend;
import com.luckv.demo.vo.Auction;
import com.luckv.demo.vo.PageInfo;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AttendController {

	private final AttendService  attendService;
	private final AuctionService auctionService;

	// /receive를 메시지를 받을 endpoint로 설정합니다.
    @MessageMapping("/attend/{ano}")
    // /send로 메시지를 반환합니다.
    @SendTo("/send/{ano}")   
    // SocketHandler는 1) /receive에서 메시지를 받고, /send로 메시지를 보내줍니다.
    // 정의한 SocketVO를 1) 인자값, 2) 반환값으로 사용합니다.
    public Attend SocketHandler(@DestinationVariable int ano, Attend attend) {
        // 반환
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
	  @PostMapping("/attend/insert")
	    public Map<String,Object> insertAuction(@ModelAttribute Attend attend) {
	        
		  	Map<String,Object> obj = new HashMap<>();
	        
	        if(!attendService.insertAttend(attend)) {
	        	obj.put("res","OK");
	        	obj.put("msg","NOT_CREATED_BOARD");
	        	return obj;
	        }
	        obj.put("res","OK");
        	obj.put("msg","SUCCESS");
        	return obj;
	    }
	    
	
		// 구매목록
	  @GetMapping("/attend/list")
	    public Map<String,Object> attendList(@ModelAttribute Auction auction) {		  	
		  	Map<String,Object> obj = new HashMap<>();

		 // 페이징 작업 세팅
	        PageInfo pageInfoVO = new PageInfo();
	        pageInfoVO.setPage(auction.getPage());
	        pageInfoVO.setRowCnt(auction.getRowCnt());

	        // 리스트 찾을때 페이징 처리 - offset값 세팅 (page * rowCnt)
	        if(auction.getPage()<=1){ auction.setPage(0);}
	        else{auction.setPage(auction.getPage()-1);}
	        auction.setPage(auction.getPage()*auction.getRowCnt());
	        List<Auction> list = attendService.attendList(auction);

	        // 토탈페이지 정보 잘라내기
	        pageInfoVO.setTotalPageCnt(list.get(list.size()-1)
	                .getTotalPageCnt());
	        list.remove(list.size() - 1);

	        obj.put("res","OK");
	        obj.put("msg","SUCCESS");
	        obj.put("list", list);
	        obj.put("pageInfo", pageInfoVO);
	        return obj;
	  	}
	  
	  	// 내 입찰목록
		@GetMapping("/attend/my")
		  public Map<String,Object> attendMy(@ModelAttribute Auction auction) {		  	
			  
			
			Map<String,Object> obj = new HashMap<>();
			
			 // 페이징 작업 세팅
	        PageInfo pageInfoVO = new PageInfo();
	        pageInfoVO.setPage(auction.getPage());
	        pageInfoVO.setRowCnt(auction.getRowCnt());

	        // 리스트 찾을때 페이징 처리 - offset값 세팅 (page * rowCnt)
	        if(auction.getPage()<=1){ auction.setPage(0);}
	        else{auction.setPage(auction.getPage()-1);}
	        auction.setPage(auction.getPage()*auction.getRowCnt());
	        List<Auction> list = attendService.attendMy(auction);

	        // 토탈페이지 정보 잘라내기
	        pageInfoVO.setTotalPageCnt(list.get(list.size()-1)
	                .getTotalPageCnt());
	        list.remove(list.size() - 1);

	        obj.put("res","OK");
	        obj.put("msg","SUCCESS");
	        obj.put("list", list);
	        obj.put("pageInfo", pageInfoVO);
	        return obj;

		 }
	  
}
