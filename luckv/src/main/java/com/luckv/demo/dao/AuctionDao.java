package com.luckv.demo.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.luckv.demo.vo.Auction;



@Repository
public class AuctionDao {
	
	private static final String NAME_SPACE = "AuctionDao.AuctionMapper.";

    @Autowired
    @Qualifier(value = "luckvSqlSession")
    private SqlSession luckvSqlSession;

	// 경매전체 리스트
    public List<Auction> auctionList(Auction auction) {
		 List<Auction> list = luckvSqlSession.selectList(NAME_SPACE+"auctionList",auction);
		 list.add(luckvSqlSession.selectOne(NAME_SPACE+"auctionCount", auction));
		 return list;
	};
	
	// 경매 등록
	public boolean insertAuction(Auction auction) {
		int count = luckvSqlSession.insert(NAME_SPACE+"insertAuction", auction);
        return count > 0 ? true : false;
	}
	
	// 경매 상세보기
	public Auction auctionDetail(int ano) {
		return  luckvSqlSession.selectOne(NAME_SPACE+"auctionDetail", ano);
	};
	
	// 경매->구독으로 변경 
	public boolean auctionChange(Auction auction) {
		int count = luckvSqlSession.update(NAME_SPACE+"auctionChange", auction);
        return count > 0 ? true : false;
	};
	
	// 경매 수정
	public boolean auctionUpdate(Auction auction) {
		int count = luckvSqlSession.update(NAME_SPACE+"auctionUpdate", auction);
        return count > 0 ? true : false;
	};
			
	// .무료 동영상 삭제		
	public boolean auctionDelete(Auction auction) {
		int count = luckvSqlSession.update(NAME_SPACE+"auctionDelete", auction);
        return count > 0 ? true : false;
	};
	
	// status 변경
	public boolean statusIngUpdate() {
		int count = luckvSqlSession.update(NAME_SPACE+"statusIngUpdate");
        return count > 0 ? true : false;
	};
}
