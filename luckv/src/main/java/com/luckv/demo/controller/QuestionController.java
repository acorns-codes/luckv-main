package com.luckv.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.luckv.demo.service.QuestionService;
import com.luckv.demo.vo.Auction;
import com.luckv.demo.vo.PageInfo;
import com.luckv.demo.vo.Question;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class QuestionController {
	private final QuestionService questionService;
	
		 
		// QnA 페이징처리
		  @GetMapping("/question/page")
		    public Map<String,Object> questionPage(Question question) {
			  
			  Map<String,Object> obj = new HashMap<>();
			  
			// 페이징 작업 세팅
		        PageInfo pageInfoVO = new PageInfo();
		        pageInfoVO.setPage(question.getPage());
		        pageInfoVO.setRowCnt(question.getRowCnt());

		        // 리스트 찾을때 페이징 처리 - offset값 세팅 (page * rowCnt)
		        if(question.getPage()<=1){ question.setPage(0);}
		        else{question.setPage(question.getPage()-1);}
		        question.setPage(question.getPage()*question.getRowCnt());
		        List<Question> list = questionService.questionPage(question);

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
		  

		   // QnA 등록
		  @PostMapping("/insert/question")
		    public Map<String,Object> insertQuestion(@RequestBody Question question) {
			  	Map<String,Object> obj = new HashMap<>();
		        boolean b = questionService.insertQuestion(question);
		        if(!b) {
		        	obj.put("res","OK");
		        	obj.put("msg","NOT_CREATED_BOARD");
		        	return obj;
		        }
		        obj.put("res","OK");
	        	obj.put("msg","SUCCESS");
	        	return obj;	        
		    }
		    
		    // QnA 상세
		    @GetMapping("/question/detail")
		    public Map<String,Object> questionDetail(Question question) {
		    	
		    	Map<String,Object> obj = new HashMap<>();
		    	Question questions= questionService.questionDetail(question);
		    	if(questions == null) {
		        	obj.put("res","OK");
		        	obj.put("msg","NOT_READ_BOARD");
		        	return obj;
		        }
		        obj.put("res","OK");
	        	obj.put("msg","SUCCESS");
	        	obj.put("data", questions);
	        	return obj;
		    	
		    }
		    
		    // QnA 수정
		    @PostMapping("/question/update")
		    public Map<String,Object> questionUpdate(@RequestBody Question question) {  
		    	Map<String,Object> obj = new HashMap<>();
		    	boolean b = questionService.questionUpdate(question);

		    	if(!b) {
		        	obj.put("res","OK");
		        	obj.put("msg","NOT_UPDATE_BOARD");
		        	return obj;
		        }
		        obj.put("res","OK");
	        	obj.put("msg","SUCCESS");
	        	return obj;	        
		    }

		    // QnA 삭제
		    @GetMapping("/question/delete")
		    public Map<String,Object> questionDelete(int qno) {  
		    	Map<String,Object> obj = new HashMap<>();
		    	boolean b = questionService.questionDelete(qno);
		    	
		    	if(!b) {
		        	obj.put("res","OK");
		        	obj.put("msg","NOT_DELETE_BOARD");
		        	return obj;
		        }
		        obj.put("res","OK");
	        	obj.put("msg","SUCCESS");
	        	return obj;	        
				
		    }

		    
		    //  QnA 댓글작성
		    @PostMapping("/question/answer")
		    public Map<String,Object> qnaAnswer(@RequestBody Question question) {
		    	Map<String,Object> obj = new HashMap<>();
		    	boolean b =  questionService.qnaAnswer(question);
		    	
		    	if(!b) {
		        	obj.put("res","OK");
		        	obj.put("msg","NOT_CREATED_BOARD");
		        	return obj;
		        }
		        obj.put("res","OK");
	        	obj.put("msg","SUCCESS");
	        	return obj;	        
		       
		    }
		    
		    //  QnA 댓글수정 
		    @PostMapping("/question/answer/update")
		    public Map<String,Object> qnaAnswerUpdate(@RequestBody Question question) {
		    	Map<String,Object> obj = new HashMap<>();
		        boolean b = questionService.qnaAnswerUpdate(question);
		        
		        if(!b) {
		        	obj.put("res","OK");
		        	obj.put("msg","NOT_UPDATE_BOARD");
		        	return obj;
		        }
		        obj.put("res","OK");
	        	obj.put("msg","SUCCESS");
	        	return obj;	        

		    }
		    
		    //  QnA 댓글
		    @GetMapping("/question/answer/list")
		    public Map<String,Object> qnaAnswerList(int qno) {
		    	Map<String,Object> obj = new HashMap<>();
		    	List<Question> question =  questionService.qnaAnswerList(qno);
		        obj.put("res","OK");
	        	obj.put("msg","SUCCESS");
	        	obj.put("data", question);
	        	return obj;

		    }
		    
		 // QnA 댓글삭제
		    @GetMapping("/question/answer/delete")
		    public Map<String,Object> qnaAnswerDelete(Question question) {  
		    	Map<String,Object> obj = new HashMap<>();
		    	boolean b = questionService.qnaAnswerDelete(question);
		    	
		    	if(!b) {
		        	obj.put("res","OK");
		        	obj.put("msg","NOT_DELETE_BOARD");
		        	return obj;
		        }
		        obj.put("res","OK");
	        	obj.put("msg","SUCCESS");
	        	return obj;
				
		    }
		    
}
