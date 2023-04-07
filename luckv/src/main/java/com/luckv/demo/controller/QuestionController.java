package com.luckv.demo.controller;

import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.luckv.demo.dto.Question;
import com.luckv.demo.response.DefaultRes;
import com.luckv.demo.response.ResponseMessage;
import com.luckv.demo.response.StatusCode;
import com.luckv.demo.service.QuestionService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class QuestionController {
	public final Logger logger = LoggerFactory.getLogger(NoticeController.class);
	private final QuestionService questionService;
	
		 
		// QnA 페이징처리
		  @GetMapping("/questionPage")
		    public ResponseEntity questionPage(Question question) {
			  
			  	
		        // 페이지 설정
		        int sn = question.getPage();   // 현재 페이지
		        int start = sn * 10 + 0; // 첫 페이지
		        int end = 10; // 끝 페이지

		        question.setStart(start);
		        question.setEnd(end);
		        
		        HashMap<String, Object>  questions = new HashMap<>();
			  	questions.put("count", questionService.questionCount(question));
			  	questions.put("questionList", questionService.questionPage(question));
		        
			  	
		        try {
					return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.READ_BOARD, questions), HttpStatus.OK);
				} catch (Exception e) {
					return new ResponseEntity(DefaultRes.res(StatusCode.NO_CONTENT, ResponseMessage.NOT_READ_BOARD), HttpStatus.OK);
					}
		    }
		  

		   // QnA 등록
		  @PostMapping("/insertQuestion")
		    public ResponseEntity insertQuestion(@RequestBody Question question) {
		        
		        boolean b = questionService.insertQuestion(question);
		        
		        if(b) {
		            return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.CREATED_BOARD, b), HttpStatus.OK);
		        }
		        return  new ResponseEntity(DefaultRes.res(StatusCode.BAD_REQUEST, ResponseMessage.NOT_CREATED_BOARD, b), HttpStatus.OK);
		    }
		    
		    // QnA 상세
		    @GetMapping("/questionDetail")
		    public ResponseEntity questionDetail(Question question) {
		    	Question questions= questionService.questionDetail(question);
		    	
		    	return questions != null? new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.READ_BOARD, questions), HttpStatus.OK)
				:  new ResponseEntity(DefaultRes.res(StatusCode.NO_CONTENT, ResponseMessage.NOT_READ_BOARD), HttpStatus.OK);
		    }
		    
		    // QnA 수정
		    @PostMapping("questionUpdate")
		    public ResponseEntity questionUpdate(@RequestBody Question question) {  
		    	boolean b = questionService.questionUpdate(question);

				  if(b) {
			            return  new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.UPDATE_BOARD, b), HttpStatus.OK);
			        }
			        return  new ResponseEntity(DefaultRes.res(StatusCode.BAD_REQUEST, ResponseMessage.NOT_UPDATE_BOARD, b), HttpStatus.OK);
		    }

		    // QnA 삭제
		    @GetMapping("questionDelete")
		    public ResponseEntity questionDelete(int qno) {  
		    	boolean b = questionService.questionDelete(qno);
				  if(b) {
			            return  new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.DELETE_BOARD, b), HttpStatus.OK);
			        }
			        return  new ResponseEntity(DefaultRes.res(StatusCode.BAD_REQUEST, ResponseMessage.NOT_DELETE_BOARD, b), HttpStatus.OK);
		    }

		    
		    //  QnA 댓글작성
		    @PostMapping("/qnaAnswer")
		    public ResponseEntity<String> qnaAnswer(@RequestBody Question question) {
		    	boolean b =  questionService.qnaAnswer(question);

		        if(b) {
		            return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.CREATED_BOARD, b), HttpStatus.OK);
		        }
		        return  new ResponseEntity(DefaultRes.res(StatusCode.BAD_REQUEST, ResponseMessage.NOT_CREATED_BOARD, b), HttpStatus.OK);
		       
		    }
		    
		    //  QnA 댓글수정 
		    @PostMapping("/qnaAnswerUpdate")
		    public ResponseEntity<String> qnaAnswerUpdate(@RequestBody Question question) {
		        boolean b = questionService.qnaAnswerUpdate(question);
				  if(b) {
			            return  new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.UPDATE_BOARD, b), HttpStatus.OK);
			        }
			        return  new ResponseEntity(DefaultRes.res(StatusCode.BAD_REQUEST, ResponseMessage.NOT_UPDATE_BOARD, b), HttpStatus.OK);
		    }
		    
		    //  QnA 댓글
		    @GetMapping("/qnaAnswerList")
		    public ResponseEntity<List<Question>> qnaAnswerList(int qno) {
		    List<Question> question =  questionService.qnaAnswerList(qno);
		    return question != null? new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.READ_BOARD, question), HttpStatus.OK)
					:  new ResponseEntity(DefaultRes.res(StatusCode.NO_CONTENT, ResponseMessage.NOT_READ_BOARD), HttpStatus.OK);

		    }
		    
		 // QnA 댓글삭제
		    @GetMapping("/qnaAnswerDelete")
		    public ResponseEntity<String> qnaAnswerDelete(Question question) {  
		    	boolean b = questionService.qnaAnswerDelete(question);
				  if(b) {
			            return  new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.DELETE_BOARD, b), HttpStatus.OK);
			        }
			        return  new ResponseEntity(DefaultRes.res(StatusCode.BAD_REQUEST, ResponseMessage.NOT_DELETE_BOARD, b), HttpStatus.OK);	        
		    }
		    
}
