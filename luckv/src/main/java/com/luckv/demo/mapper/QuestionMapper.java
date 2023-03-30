package com.luckv.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.luckv.demo.dto.Question;

@Mapper
@Repository
public interface QuestionMapper {

	// QnA 갯수
	int questionCount(Question question);
	
	// QnA 페이지처리
	List<Question> questionPage(Question question);
	
	// QnA 등록
	int insertQuestion(Question question);
	
	// QnA 상세보기
	Question questionDetail(int qno);
	
	// QnA 수정
	int questionUpdate(Question question);
	
	// QnA 삭제
	int questionDelete(int qno);
	
	
	// QnA 답변작성
	int qnaAnswer(Question question);
	
	// QnA 답변수정
	int qnaAnswerUpdate(Question question); 
    
    // QnA 답변
    List<Question> qnaAnswerList(int qno);
    
    // QnA 답변삭제
    int qnaAnswerDelete(Question question);
    
}
