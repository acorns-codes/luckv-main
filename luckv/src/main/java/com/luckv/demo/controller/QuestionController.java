package com.luckv.demo.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luckv.demo.dto.Frequently;
import com.luckv.demo.dto.Question;
import com.luckv.demo.service.FrequentlyService;
import com.luckv.demo.service.QuestionService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class QuestionController {
	public final Logger logger = LoggerFactory.getLogger(NoticeController.class);
	private final QuestionService questionService;
	
		 // FaQ 조회
		 @GetMapping("/questionList")
		    public List<Question> questionList(Question question) {
		        logger.info("QuestionController questionList");
		        return questionService.questionList(question);
		    }
		 
		  // FaQ 갯수
		 @GetMapping("/questionCount")
		    public int questionCount(Question question) {
		        logger.info("QuestionController questionCount");
		        return questionService.questionCount(question);
		    }
		 
		// 공지사항 페이징처리
		  @GetMapping("/questionPage")
		    public List<Question> questionPage(Question question) {
		        logger.info("QuestionController questionPage");

		        // 페이지 설정
		        int sn = question.getPage();   // 현재 페이지
		        int start = sn * 10 + 1; // 첫 페이지
		        int end = (sn + 1) * 10; // 끝 페이지

		        question.setStart(start);
		        question.setEnd(end);

		        return questionService.questionPage(question);
		    }
		  

		   // FaQ 등록
		    @GetMapping("/insertQuestion")
		    public void insertQuestion(Question question) {
		        logger.info("QuestionController insertQuestion()");
		        questionService.insertQuestion(question);
		    }
		    
		    // FaQ 상세
		    @GetMapping("/questionDetail")
		    public Question questionDetail(int qno) {
		        logger.info("QuestionController questionDetail()");
		        return questionService.questionDetail(qno);
		    }
		    
		    // FaQ 수정
		    @GetMapping("questionUpdate")
		    public void questionUpdate(Question question) {  
		        logger.info("QuestionController questionUpdate()");
		        questionService.questionUpdate(question);
		    }

		    // FaQ 삭제
		    @GetMapping("questionDelete")
		    public void questionDelete(int qno) {  
		        logger.info("QuestionController questionDelete()");
		        questionService.questionDelete(qno);
		    }
		    
}
