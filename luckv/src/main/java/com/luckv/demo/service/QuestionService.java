package com.luckv.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.luckv.demo.dao.QuestionDao;
import com.luckv.demo.vo.Question;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class QuestionService {

	private final QuestionDao questionDao;


	// QnA 페이징처리
	public List<Question> questionPage(Question question) {
        return questionDao.questionPage(question);
	}
	
	// QnA 등록
	public boolean insertQuestion(Question question) {
		return questionDao.insertQuestion(question);
       
	}
	
	// QnA 상세
	public Question questionDetail(Question question) {
        return questionDao.questionDetail(question);
	}

	// QnA 수정
	public boolean questionUpdate(Question question) {
		return questionDao.questionUpdate(question); 
	}

	// QnA 삭제
	public boolean questionDelete(int qno) {
		return questionDao.questionDelete(qno);          
	}
	
	// QnA 댓글 등록
	public boolean qnaAnswer(Question question) { 
		return questionDao.qnaAnswer(question);     		
    }

	// QnA 댓글 수정
    public boolean qnaAnswerUpdate(Question question) {
    	return questionDao.qnaAnswerUpdate(question);
          		
    }
    
    // QnA 댓글목록
    public List<Question> qnaAnswerList(int qno) {
		return questionDao.qnaAnswerList(qno);
	}
    
    // QnA 댓글 삭제
    public boolean qnaAnswerDelete(Question question) {
    	return questionDao.qnaAnswerDelete(question);
        
	}

}
