package com.luckv.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.luckv.demo.service.NoticeService;
import com.luckv.demo.vo.Auction;
import com.luckv.demo.vo.Notice;
import com.luckv.demo.vo.PageInfo;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class NoticeController {
	
	private final NoticeService noticeService;
	

	   // 공지사항 페이징처리
	  @GetMapping("/notice/list")
	    public Map<String,Object> noticeList(@ModelAttribute Notice notice) {	  	
	        
		  Map<String,Object> obj = new HashMap<>();
		// 페이징 작업 세팅
	        PageInfo pageInfoVO = new PageInfo();
	        pageInfoVO.setPage(notice.getPage());
	        pageInfoVO.setRowCnt(notice.getRowCnt());

	        // 리스트 찾을때 페이징 처리 - offset값 세팅 (page * rowCnt)
	        if(notice.getPage()<=1){ notice.setPage(0);}
	        else{notice.setPage(notice.getPage()-1);}
	        notice.setPage(notice.getPage()*notice.getRowCnt());
	        List<Notice> list = noticeService.noticeList(notice);

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

	   // 공지사항 등록
	  @PostMapping("/insert/notice")
	    public Map<String,Object> insertNotice(@RequestBody Notice notice) {
		  	Map<String,Object> obj = new HashMap<>();
	        
	        if(!noticeService.insertNotice(notice)) {
	        	obj.put("res","OK");
	        	obj.put("msg","NOT_CREATED_BOARD");
	        	return obj;
	        }
	        obj.put("res","OK");
        	obj.put("msg","SUCCESS");
        	return obj;
	    }
	  
	    // 공지사항 상세
	    @GetMapping("/notice/detail")
	    public Map<String,Object> noticeDetail(int nno) {
	    	Map<String,Object> obj = new HashMap<>();    	
		    Notice notice = noticeService.noticeDetail(nno); 
		    if(notice == null) {
	        	obj.put("res","OK");
	        	obj.put("msg","NOT_READ_BOARD");
	        	return obj;
	        }
	        obj.put("res","OK");
        	obj.put("msg","SUCCESS");
        	obj.put("data", notice);
        	return obj;
	    }
	    
	    // 공지사항 수정
	    @PostMapping("/notice/update")
	    public Map<String,Object> noticeUpdate(@RequestBody Notice notice) {  
	    	Map<String,Object> obj = new HashMap<>();
	    	
	    	 if(!noticeService.noticeUpdate(notice)) {
		        	obj.put("res","OK");
		        	obj.put("msg","NOT_UPDATE_BOARD");
		        	return obj;
		        }
		        obj.put("res","OK");
	        	obj.put("msg","SUCCESS");
	        	return obj;	        
	    }
	    
}
