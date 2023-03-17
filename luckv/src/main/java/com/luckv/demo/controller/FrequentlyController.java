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

import com.luckv.demo.dto.Frequently;
import com.luckv.demo.response.DefaultRes;
import com.luckv.demo.response.ResponseMessage;
import com.luckv.demo.response.StatusCode;
import com.luckv.demo.service.FrequentlyService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class FrequentlyController {
	
	public final Logger logger = LoggerFactory.getLogger(NoticeController.class);
	private final FrequentlyService frequentlyService;
	
		 // FaQ 조회
		 @GetMapping("/frequentlyList")
		    public ResponseEntity<List<Frequently>> frequentlyList(Frequently frequently) {
			 List<Frequently> faq =   frequentlyService.frequentlyList(frequently);
			 try {
					return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.READ_BOARD, faq), HttpStatus.OK);
				} catch (Exception e) {
					return new ResponseEntity(DefaultRes.res(StatusCode.NO_CONTENT, ResponseMessage.NOT_READ_BOARD), HttpStatus.OK);
					}
			 }
		 
		  // FaQ 갯수
		 @GetMapping("/frequentlyCount")
		    public int frequentlyCount(Frequently frequently) {
		        return frequentlyService.frequentlyCount(frequently);
		    }
		 
		// FaQ 페이징처리
		  @GetMapping("/frequentlyPage")
		    public List<Frequently> frequentlyPage(Frequently frequently) {

		        // 페이지 설정
		        int sn = frequently.getPage();   // 현재 페이지
		        int start = sn * 10 + 1; // 첫 페이지
		        int end = (sn + 1) * 10; // 끝 페이지

		        frequently.setStart(start);
		        frequently.setEnd(end);

		        return frequentlyService.frequentlyPage(frequently);
		    }
		  

		   // FaQ 등록
		  @PostMapping("/insertFrequently")
		    public ResponseEntity<String> insertFrequently(@RequestBody Frequently frequently) {		       
		        
		        boolean b = frequentlyService.insertFrequently(frequently);
		        
		        if(b) {
		            return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.CREATED_BOARD, b), HttpStatus.OK);
		        }
		        return  new ResponseEntity(DefaultRes.res(StatusCode.BAD_REQUEST, ResponseMessage.NOT_CREATED_BOARD, b), HttpStatus.OK);
		    }
		    
		    // FaQ 상세
//		    @GetMapping("/frequentlyDetail")
//		    public Frequently frequentlyDetail(int fno) {
//		        logger.info("FrequentlycController frequentlyDetail()");
//		        return frequentlyService.frequentlyDetail(fno);
//		    }
		    
		    // FaQ 수정
		  @PostMapping("frequentlyUpdate")
		    public ResponseEntity<String> frequentlyUpdate(@RequestBody Frequently frequently) {  
			  boolean b = frequentlyService.frequentlyUpdate(frequently);
			  if(b) {
		            return  new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.UPDATE_BOARD, b), HttpStatus.OK);
		        }
		        return  new ResponseEntity(DefaultRes.res(StatusCode.BAD_REQUEST, ResponseMessage.NOT_UPDATE_BOARD, b), HttpStatus.OK);
		    }

		    // FaQ 삭제
		    @GetMapping("frequentlyDelete")
		    public ResponseEntity<String> frequentlyDelete(int fno) {  	        
		        boolean b = frequentlyService.frequentlyDelete(fno);
				  if(b) {
			            return  new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.DELETE_BOARD, b), HttpStatus.OK);
			        }
			        return  new ResponseEntity(DefaultRes.res(StatusCode.BAD_REQUEST, ResponseMessage.NOT_DELETE_BOARD, b), HttpStatus.OK);
		    }
		    
}
