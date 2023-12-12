package com.luckv.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.luckv.demo.vo.Question;


@Repository
public class QuestionDao {

	// QnA 갯수
//	boolean questionCount(Question question);
	

	private static final String NAME_SPACE = "QuestionDao.QuestionMapper.";
	
	@Autowired
    @Qualifier(value = "luckvSqlSession")
    private SqlSession luckvSqlSession;
	
	
	// QnA 리스트
	public List<Question> questionPage(Question question)  {
		List<Question> list = luckvSqlSession.selectList(NAME_SPACE+"questionPage", question);
        list.add(luckvSqlSession.selectOne(NAME_SPACE+"questionCount", question));
        return list;
	};
	
	// QnA 등록
	public boolean insertQuestion(Question question) {
		int count = luckvSqlSession.insert(NAME_SPACE+"insertQuestion",question);
        return count > 0 ? true : false;
	};
	
	// QnA 상세보기
	public Question questionDetail(Question question) {
        return luckvSqlSession.selectOne(NAME_SPACE+"questionDetail",question);
	};
	
	// QnA 수정
	public boolean questionUpdate(Question question) {
		int count = luckvSqlSession.update(NAME_SPACE+"questionUpdate",question);
        return count > 0 ? true : false;
	};
	
	// QnA 삭제
	public boolean questionDelete(int qno) {
		int count = luckvSqlSession.update(NAME_SPACE+"questionDelete",qno);
        return count > 0 ? true : false;
	};
	
	
	// QnA 답변작성
	public boolean qnaAnswer(Question question) {
		int count = luckvSqlSession.insert(NAME_SPACE+"qnaAnswer",question);
        return count > 0 ? true : false;
	};
	
	// QnA 답변수정
	public boolean qnaAnswerUpdate(Question question) {
		int count = luckvSqlSession.update(NAME_SPACE+"qnaAnswerUpdate",question);
        return count > 0 ? true : false;
	}; 
    
    // QnA 답변
	public List<Question> qnaAnswerList(int qno) {
    	List<Question> list = luckvSqlSession.selectList(NAME_SPACE+"qnaAnswerList", qno);
        return list;
    };
    
    // QnA 답변삭제
    public boolean qnaAnswerDelete(Question question) {
    	int count = luckvSqlSession.update(NAME_SPACE+"qnaAnswerDelete",question);
        return count > 0 ? true : false;
    };
    
}
