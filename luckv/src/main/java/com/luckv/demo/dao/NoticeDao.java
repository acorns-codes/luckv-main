package com.luckv.demo.dao;


import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.luckv.demo.vo.Notice;


@Repository
public class  NoticeDao {
	
	// 공지사항 갯수
//	boolean noticeCount(Notice notice) {
//		
//	};
	private static final String NAME_SPACE = "NoticeDao.NoticeMapper.";

    @Autowired
    @Qualifier(value = "luckvSqlSession")
    private SqlSession luckvSqlSession;

	
	// 공지사항 페이지처리
    public List<Notice> noticeList(Notice notice) {
		List<Notice> list = luckvSqlSession.selectList(NAME_SPACE+"noticeList",notice);
		 list.add(luckvSqlSession.selectOne(NAME_SPACE+"noticeCount", notice));
		 return list;
	};
	
	// 공지사항 등록
	public boolean insertNotice(Notice notice) {
		int count = luckvSqlSession.insert(NAME_SPACE+"insertNotice", notice);
        return count > 0 ? true : false;
	};
	
	// 공지사항 상세보기
	public Notice noticeDetail(int nno) {
		return  luckvSqlSession.selectOne(NAME_SPACE+"noticeDetail", nno);
	};
	
	// 공지사항 수정
	public boolean noticeUpdate(Notice notice) {
		int count = luckvSqlSession.insert(NAME_SPACE+"noticeUpdate", notice);
        return count > 0 ? true : false;
	};
	
	

}

