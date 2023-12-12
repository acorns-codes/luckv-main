package com.luckv.demo.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.luckv.demo.dao.VideoDao;
import com.luckv.demo.service.AuctionService;
import com.luckv.demo.service.UserService;
import com.luckv.demo.vo.Auction;
import com.luckv.demo.vo.PageInfo;
import com.luckv.demo.vo.Video;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AuctionController {
	
		private final AuctionService auctionService;
		private final UserService userService;
		private final VideoDao videoMapper;
		
		@Value("${file.dir}") //application.properties에 있는 속성 그대로 가져오기
	    private String fileDir;
		
			// 경매 리스트
		  @GetMapping("/auction/list")
		    public Map<String,Object> auctionList(@ModelAttribute Auction auction) {
			  
			  Map<String,Object> obj = new HashMap<>();
			  		  
			  	// 페이징 작업 세팅
		        PageInfo pageInfoVO = new PageInfo();
		        pageInfoVO.setPage(auction.getPage());
		        pageInfoVO.setRowCnt(auction.getRowCnt());

		        // 리스트 찾을때 페이징 처리 - offset값 세팅 (page * rowCnt)
		        if(auction.getPage()<=1){ auction.setPage(0);}
		        else{auction.setPage(auction.getPage()-1);}
		        auction.setPage(auction.getPage()*auction.getRowCnt());
		        List<Auction> list = auctionService.auctionList(auction);

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
		   

		   // 판매 등록
		  @PostMapping("/auction/insert")
		    public Map<String,Object> insertAuction(@ModelAttribute Auction auction) {
		        
			  Map<String,Object> obj = new HashMap<>();
		        
		        if(!auctionService.insertAuction(auction)) {
		        	obj.put("res","OK");
		        	obj.put("msg","NOT_CREATED_BOARD");
		        	return obj;
		        }
		        obj.put("res","OK");
	        	obj.put("msg","SUCCESS");
	        	return obj;
		    }
		    
		    // 판매 상세
		    @GetMapping("/auction/detail")
		    public Map<String,Object> auctionDetail(int ano) {
		    	Map<String,Object> obj = new HashMap<>();
		    	Auction auction = auctionService.auctionDetail(ano);
		    	userService.videoSubAuto();
		    	
		    	if (auction == null) {
		    		obj.put("res","OK");
		        	obj.put("msg","NOT_READ_BOARD");
		        	return obj;
		    	}
		    	
		    	obj.put("res","OK");
	        	obj.put("msg","SUCCESS");
	        	obj.put("data", auction);
	        	return obj;
	        	
		    }
		    
		    // 판매 수정
		    @PostMapping("/auction/update")
		    public Map<String,Object> auctionUpdate(@ModelAttribute Auction auction) {  
		    	
		    	Map<String,Object> obj = new HashMap<>();
		    	
		    	 if(!auctionService.auctionUpdate(auction)) {
			        	obj.put("res","OK");
			        	obj.put("msg","NOT_UPDATE_BOARD");
			        	return obj;
			        }
			        obj.put("res","OK");
		        	obj.put("msg","SUCCESS");
		        	return obj;
		    }
		    
		    // 경매->구독으로 변경 
		    @PostMapping("/auction/change")
		    public Map<String,Object> auctionChange(@ModelAttribute Auction auction) {  
		    	Map<String,Object> obj = new HashMap<>();
		    			    	
		    	if(!auctionService.auctionChange(auction)) {
		        	obj.put("res","OK");
		        	obj.put("msg","NOT_CHANGE_AUCTION");
		        	return obj;
		        }
		        obj.put("res","OK");
	        	obj.put("msg","SUCCESS");
	        	return obj;
		
		    }
		    
		    
		    // 무료 동영상 삭제
		    @GetMapping("/auction/delete")
		    public Map<String,Object> auctionDelete(@ModelAttribute Auction auction, HttpServletRequest request) throws IOException {  	        
		        
		    	Map<String,Object> obj = new HashMap<>();
		    	
		        int  ano = auction.getAno();
		        Video video = videoMapper.videoPlay(ano);
		        String videoName = video.getVideoFile();
		        File file = new File(fileDir, videoName);
		 
		        if ( file.exists()&& file.isFile())
		        {
		        	file.delete();       // 파일 삭제
		        }

		        if(!auctionService.auctionDelete(auction)) {
		        	obj.put("res","OK");
		        	obj.put("msg","NOT_DELETE_BOARD");
		        	return obj;
		        }
		        obj.put("res","OK");
	        	obj.put("msg","SUCCESS");
	        	return obj;
			
		    }

		    
		    
}
