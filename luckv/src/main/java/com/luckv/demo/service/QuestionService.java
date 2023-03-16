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
	
	public List<Question> questionList(Question question) {
		return questionMapper.questionList(question);
	}

	public int questionCount(Question question) {
		return questionMapper.questionCount(question);
	}

	public List<Question> questionPage(Question question) {
        return questionMapper.questionPage(question);
	}

	public void insertQuestion(Question question) {
        questionMapper.insertQuestion(question);
	}

	public Question questionDetail(int qno) {
		questionMapper.qnaAnswerList(qno);
        return questionMapper.questionDetail(qno);
	}

	public void questionUpdate(Question question) {
        questionMapper.questionUpdate(question);
	}

	public void questionDelete(int qno) {
        questionMapper.questionDelete(qno);
	}
	
	// QnA 댓글
	public void qnaAnswer(Question question) { 
		questionMapper.qnaAnswer(question);
    }

    public void qnaAnswerUpdate(Question question) {
        questionMapper.qnaAnswerUpdate(question);
    }
    
    public List<Question> qnaAnswerList(int qno) {
		return questionMapper.qnaAnswerList(qno);
	}
    
    public void qnaAnswerDelete(Question question) {
        questionMapper.qnaAnswerDelete(question);
	}

}
