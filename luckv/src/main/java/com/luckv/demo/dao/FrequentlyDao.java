package com.luckv.demo.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.luckv.demo.vo.Frequently;


@Repository
public class FrequentlyDao {
	private static final String NAME_SPACE = "FrequentlyDao.FrequentlyMapper.";
	
	@Autowired
    @Qualifier(value = "luckvSqlSession")
    private SqlSession luckvSqlSession;


	// FaQ 페이지처리
	public List<Frequently> frequentlyList(Frequently frequently) {
		List<Frequently> list = luckvSqlSession.selectList(NAME_SPACE+"frequentlyList", frequently);
        list.add(luckvSqlSession.selectOne(NAME_SPACE+"frequentlyCount", frequently));
        return list;
		
	};
	
	// FaQ 등록
	public boolean insertFrequently(Frequently frequently) {
		int count = luckvSqlSession.insert(NAME_SPACE+"insertFrequently",frequently);
        return count > 0 ? true : false;
	};
	
	// FaQ 상세보기
	public Frequently frequentlyDetail(int fno) {
		return luckvSqlSession.selectOne(NAME_SPACE+"frequentlyDetail", fno);
	};
	
	// FaQ 수정
	public boolean frequentlyUpdate(Frequently frequently) {
		int count = luckvSqlSession.update(NAME_SPACE+"frequentlyUpdate",frequently);
        return count > 0 ? true : false;
	};
	
	// FaQ 삭제
	public boolean frequentlyDelete(int fno) {
		int count = luckvSqlSession.update(NAME_SPACE+"frequentlyDelete",fno);
        return count > 0 ? true : false;
	};
}
