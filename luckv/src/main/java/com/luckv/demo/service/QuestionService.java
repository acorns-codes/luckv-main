package com.luckv.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.luckv.demo.dto.Question;
import com.luckv.demo.mapper.QuestionMapper;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class QuestionService {

	private final QuestionMapper questionMapper;
	
	// QnA 갯수
	public int questionCount(Question question) {
		return questionMapper.questionCount(question);
	}

	// QnA 페이징처리
	public List<Question> questionPage(Question question) {
        return questionMapper.questionPage(question);
	}
	
	// QnA 등록
	public boolean insertQuestion(Question question) {
		int n = questionMapper.insertQuestion(question);
        return n > 0;
	}
	
	// QnA 상세
	public Question questionDetail(Question question) {
        return questionMapper.questionDetail(question);
	}

	// QnA 수정
	public boolean questionUpdate(Question question) {
		int n = questionMapper.questionUpdate(question);
        return n > 0;
	}

	// QnA 삭제
	public boolean questionDelete(int qno) {
		int n = questionMapper.questionDelete(qno);
        return n > 0;     
	}
	
	// QnA 댓글 등록
	public boolean qnaAnswer(Question question) { 
		int n = questionMapper.qnaAnswer(question);
        return n > 0;   		
    }

	// QnA 댓글 수정
    public boolean qnaAnswerUpdate(Question question) {
    	int n = questionMapper.qnaAnswerUpdate(question);
        return n > 0;   		
    }
    
    // QnA 댓글목록
    public List<Question> qnaAnswerList(int qno) {
		return questionMapper.qnaAnswerList(qno);
	}
    
    // QnA 댓글 삭제
    public boolean qnaAnswerDelete(Question question) {
    	int n = questionMapper.qnaAnswerDelete(question);
        return n > 0; 
	}

}
