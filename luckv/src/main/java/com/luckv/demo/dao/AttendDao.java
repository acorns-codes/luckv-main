package com.luckv.demo.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.luckv.demo.vo.Attend;
import com.luckv.demo.vo.Auction;

@Repository
public class AttendDao {

		private static final String NAME_SPACE = "AttendDao.AttendMapper.";
		
		@Autowired
	    @Qualifier(value = "luckvSqlSession")
	    private SqlSession luckvSqlSession;
		
		// 입찰등록
		public boolean insertAttend(Attend Attend) {
			int count = luckvSqlSession.insert(NAME_SPACE+"insertAttend",Attend);
	        return count > 0 ? true : false;
		}
		
		// 구매목록
		public List<Auction> attendList(Auction auction) {
			List<Auction> list = luckvSqlSession.selectList(NAME_SPACE+"attendList", auction);
	        list.add(luckvSqlSession.selectOne(NAME_SPACE+"attendCount", auction));
	        return list;
		}

		// 내 입찰 목록
		public List<Auction> attendMy(Auction auction) {
			List<Auction> list = luckvSqlSession.selectList(NAME_SPACE+"attendMy", auction);
	        list.add(luckvSqlSession.selectOne(NAME_SPACE+"attendMyCount", auction));
	        return list;
		};

}
		