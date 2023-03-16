package com.luckv.demo.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luckv.demo.dto.Frequently;
import com.luckv.demo.service.FrequentlyService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class FrequentlyController {
	
	public final Logger logger = LoggerFactory.getLogger(NoticeController.class);
	private final FrequentlyService frequentlyService;
	
		 // FaQ 조회
		 @GetMapping("/frequentlyList")
		    public List<Frequently> frequentlyList(Frequently frequently) {
		        logger.info("FrequentlyController frequentlyList");
		        return frequentlyService.frequentlyList(frequently);
		    }
		 
		  // FaQ 갯수
		 @GetMapping("/frequentlyCount")
		    public int frequentlyCount(Frequently frequently) {
		        logger.info("FrequentlyController frequentlyCount");
		        return frequentlyService.frequentlyCount(frequently);
		    }
		 
		// 공지사항 페이징처리
		  @GetMapping("/frequentlyPage")
		    public List<Frequently> frequentlyPage(Frequently frequently) {
		        logger.info("FrequentlyController frequentlyPage");

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
		    public void insertFrequently(Frequently frequently) {
		        logger.info("FrequentlyController insertFrequently()");
		        frequentlyService.insertFrequently(frequently);
		    }
		    
		    // FaQ 상세
//		    @GetMapping("/frequentlyDetail")
//		    public Frequently frequentlyDetail(int fno) {
//		        logger.info("FrequentlycController frequentlyDetail()");
//		        return frequentlyService.frequentlyDetail(fno);
//		    }
		    
		    // FaQ 수정
		  @PostMapping("frequentlyUpdate")
		    public void frequentlyUpdate(Frequently frequently) {  
		        logger.info("FrequentlyController frequentlyUpdate()");
		        frequentlyService.frequentlyUpdate(frequently);
		    }

		    // FaQ 삭제
		    @GetMapping("frequentlyDelete")
		    public void frequentlyDelete(int fno) {  
		        logger.info("FrequentlyController frequentlyDelete()");
		        frequentlyService.frequentlyDelete(fno);
		    }
		    
}
