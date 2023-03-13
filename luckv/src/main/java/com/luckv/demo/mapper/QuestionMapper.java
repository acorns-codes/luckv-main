package com.luckv.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.luckv.demo.dto.Frequently;
import com.luckv.demo.dto.Question;

@Mapper
@Repository
public interface QuestionMapper {

	List<Question> questionList(Question question);

	// FaQ 갯수
	int questionCount(Question question);
	
	// FaQ 페이지처리
	List<Question> questionPage(Question question);
	
	// FaQ 등록
	void insertQuestion(Question question);
	
	// FaQ 상세보기
	Question questionDetail(int qno);
	
	// FaQ 수정
	void questionUpdate(Question question);
	
	// FaQ 삭제
	void questionDelete(int qno);
}
