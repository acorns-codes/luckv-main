package com.luckv.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.luckv.demo.dto.Notice;
import com.luckv.demo.response.DefaultRes;
import com.luckv.demo.response.ResponseMessage;
import com.luckv.demo.response.StatusCode;
import com.luckv.demo.service.NoticeService;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class NoticeController {
	
	public final Logger logger = LoggerFactory.getLogger(NoticeController.class);
	private final NoticeService noticeService;
	
	  // 공지사항 조회
	 @GetMapping("/noticeList")
	    public List<Notice> noticeList() {
	        logger.info("NoticeController noticeList");
	        return noticeService.noticeList();
	    }
	 
	  // 공지사항 갯수
	 @GetMapping("/noticeCount")
	    public int noticeCount(Notice notice) {
	        logger.info("NoticeController noticeCount");
	        return noticeService.noticeCount(notice);
	    }
	 
	   // 공지사항 페이징처리
	  @GetMapping("/noticePage")
	    public List<Notice> noticePage(Notice notice) {
	        logger.info("NoticeController noticePage");

	        // 페이지 설정
	        int sn = notice.getPage();   // 현재 페이지
	        int start = sn * 10 + 1; // 첫 페이지
	        int end = (sn + 1) * 10; // 끝 페이지

	        notice.setStart(start);
	        notice.setEnd(end);

	        return noticeService.noticePage(notice);
	    }

	   // 공지사항 등록
	  @PostMapping("/insertNotice")
	    public ResponseEntity<String> insertNotice(@RequestBody Notice notice) {
	        logger.info("NoticeController insertNotice()");
	        boolean b = noticeService.insertNotice(notice);

	        if(b) {
	            return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.CREATED_BOARD, b), HttpStatus.OK);
	        }
	        return  new ResponseEntity(DefaultRes.res(StatusCode.BAD_REQUEST, ResponseMessage.NOT_CREATED_BOARD, b), HttpStatus.OK);
	    }
	    // 공지사항 상세
	    @GetMapping("/noticeDetail")
	    public Notice noticeDetail(int nno) {
	        logger.info("NoticeController noticeDetail()");
	        return noticeService.noticeDetail(nno);
	    }
	    
	    // 공지사항 수정
	    @PostMapping("noticeUpdate")
	    public void noticeUpdate(Notice notice) {  
	        logger.info("NoticeController noticeUpdate()");
	        noticeService.noticeUpdate(notice);
	    }
	    
}
