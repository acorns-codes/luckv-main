package com.luckv.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.luckv.demo.service.FrequentlyService;
import com.luckv.demo.vo.Auction;
import com.luckv.demo.vo.Frequently;
import com.luckv.demo.vo.PageInfo;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class FrequentlyController {
	
	private final FrequentlyService frequentlyService;
	

		// FaQ 리스트
		  @GetMapping("/frequently/list")
		    public Map<String,Object> frequentlyList(@ModelAttribute Frequently frequently) {

			  	Map<String,Object> obj = new HashMap<>();
			  	
			  	// 페이징 작업 세팅
		        PageInfo pageInfoVO = new PageInfo();
		        pageInfoVO.setPage(frequently.getPage());
		        pageInfoVO.setRowCnt(frequently.getRowCnt());

		        // 리스트 찾을때 페이징 처리 - offset값 세팅 (page * rowCnt)
		        if(frequently.getPage()<=1){ frequently.setPage(0);}
		        else{frequently.setPage(frequently.getPage()-1);}
		        frequently.setPage(frequently.getPage()*frequently.getRowCnt());
		        List<Frequently> list = frequentlyService.frequentlyList(frequently);

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
		  

		   // FaQ 등록
		  @PostMapping("/insert/frequently")
		    public Map<String,Object> insertFrequently(@RequestBody Frequently frequently) {		       
			  	Map<String,Object> obj = new HashMap<>();
		        
		        if(!frequentlyService.insertFrequently(frequently)) {
		        	obj.put("res","OK");
		        	obj.put("msg","NOT_CREATED_BOARD");
		        	return obj;
		        }
		        obj.put("res","OK");
	        	obj.put("msg","SUCCESS");
	        	return obj;
		    }
		    
		    // FaQ 상세
		    @GetMapping("/frequently/detail")
		    public Map<String,Object> frequentlyDetail(int fno) {
		    	
		    	Map<String,Object> obj = new HashMap<>();		    	
		    	Frequently detail =  frequentlyService.frequentlyDetail(fno);
		    	obj.put("res","OK");
	        	obj.put("msg","SUCCESS");
	        	obj.put("data", detail); 
	        	return obj;
		    	
		    }
		    
		    // FaQ 수정
		  @PostMapping("/frequently/update")
		    public Map<String,Object> frequentlyUpdate(@RequestBody Frequently frequently) {  
			  Map<String,Object> obj = new HashMap<>();
			  if(!frequentlyService.frequentlyUpdate(frequently)) {
		        	obj.put("res","OK");
		        	obj.put("msg","NOT_UPDATE_BOARD");
		        	return obj;
		        }
		        obj.put("res","OK");
	        	obj.put("msg","SUCCESS");
	        	return obj;
		    }

		    // FaQ 삭제
		    @GetMapping("/frequently/delete")
		    public Map<String,Object> frequentlyDelete(int fno) {  	        
		    	Map<String,Object> obj = new HashMap<>();
		        
		        if(!frequentlyService.frequentlyDelete(fno)) {
		        	obj.put("res","OK");
		        	obj.put("msg","NOT_DELETE_BOARD");
		        	return obj;
		        }
		        obj.put("res","OK");
	        	obj.put("msg","SUCCESS");
	        	return obj;
		    }
		    
}
