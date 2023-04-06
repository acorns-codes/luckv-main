package com.luckv.demo.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.luckv.demo.dto.Auction;
import com.luckv.demo.dto.Video;
import com.luckv.demo.mapper.VideoMapper;
import com.luckv.demo.response.DefaultRes;
import com.luckv.demo.response.ResponseMessage;
import com.luckv.demo.response.StatusCode;
import com.luckv.demo.service.AuctionService;
import com.luckv.demo.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AuctionController {
	
		public final Logger logger = LoggerFactory.getLogger(NoticeController.class);
		private final AuctionService auctionService;
		private final UserService userService;
		private final VideoMapper videoMapper;
		
		@Value("${file.dir}") //application.properties에 있는 속성 그대로 가져오기
	    private String fileDir;
		
		// 경매 전체리스트
		  @GetMapping("/auctionAll")
		    public ResponseEntity auctionAll(Auction auction) {
	
		        // 페이지 설정
		        int sn = auction.getPage();   // 현재 페이지
		        int start = sn * 10 + 0; // 첫 페이지
		        int end = (sn + 1) * 10; // 끝 페이지

		        auction.setStart(start);
		        auction.setEnd(end);		    	
		        
		        HashMap<String, Object>  auctions = new HashMap<>();
		        auctions.put("count", auctionService.auctionCount(auction));
		        auctions.put("auctionList", auctionService.auctionAll(auction));
		        
				 try {
					return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.READ_BOARD, auctions), HttpStatus.OK);
					} catch (Exception e) {
						return new ResponseEntity(DefaultRes.res(StatusCode.NO_CONTENT, ResponseMessage.NOT_READ_BOARD), HttpStatus.OK);
					}
		    }
		  
			// 판매 리스트 페이징처리
		  @GetMapping("/auctionPage")
		    public ResponseEntity auctionPage(Auction auction) {

		        // 페이지 설정
		        int sn = auction.getPage();   // 현재 페이지
		        int start = sn * 12 + 0; // 첫 페이지
		        int end = (sn + 1) * 12; // 끝 페이지

		        auction.setStart(start);
		        auction.setEnd(end);

		        HashMap<String, Object>  auctions = new HashMap<>();
		        auctions.put("count", auctionService.auctionCount(auction));
		        auctions.put("auctionList", auctionService.auctionPage(auction));
		        
		        try {
					return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.READ_BOARD, auctions), HttpStatus.OK);
					} catch (Exception e) {
						return new ResponseEntity(DefaultRes.res(StatusCode.NO_CONTENT, ResponseMessage.NOT_READ_BOARD), HttpStatus.OK);
					}
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
		    	userService.videoSubAuto();
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
		    
		    // 경매->구독으로 변경 
		    @PostMapping("auctionChange")
		    public ResponseEntity auctionChange(@RequestBody Auction auction) {  
		    	boolean b = auctionService.auctionChange(auction);

				  if(b) {
			            return  new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.CHANGE_AUCTION, b), HttpStatus.OK);
			        }
			        return  new ResponseEntity(DefaultRes.res(StatusCode.BAD_REQUEST, ResponseMessage.NOT_CHANGE_AUCTION, b), HttpStatus.OK);
		    }
		    
		    
		    // 무료 동영상 삭제
		    @GetMapping("auctionDelete")
		    public ResponseEntity<String> auctionDelete(Auction auction, HttpServletRequest request) throws IOException {  	        
		        
		        int  ano = auction.getAno();
		        Video video = videoMapper.videoPlay(ano);
		        String videoName = video.getVideoFile();
		        File file = new File(fileDir, videoName);
		 
		        if ( file.exists()&& file.isFile())
		        {
		        	file.delete();       // 파일 삭제
		        }
		        boolean b = auctionService.auctionDelete(auction);

				  if(b) {
			            return  new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.DELETE_BOARD, b), HttpStatus.OK);
			        }
			        return  new ResponseEntity(DefaultRes.res(StatusCode.BAD_REQUEST, ResponseMessage.NOT_DELETE_BOARD, b), HttpStatus.OK);
		    }

		    
		    
}
