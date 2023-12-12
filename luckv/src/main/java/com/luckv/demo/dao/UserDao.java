package com.luckv.demo.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.luckv.demo.vo.User;

@Repository
public class UserDao {
	
	private static final String NAME_SPACE = "UserDao.UserMapper.";
	
	@Autowired
    @Qualifier(value = "luckvSqlSession")
    private SqlSession luckvSqlSession;
	
	// 아이디 중복확인
	public boolean getId(User user) {
		int count = luckvSqlSession.selectOne(NAME_SPACE+"getId",user);
        return count > 0 ? true : false;
	};
	
	// 회원가입
	public boolean addMember(User user) {
		int count = luckvSqlSession.insert(NAME_SPACE+"addMember",user);
        return count > 0 ? true : false;
    };
    
    // 로그인
    public User login(User user) {
    	return luckvSqlSession.selectOne(NAME_SPACE+"login", user);
    };

	// 회원정보 조회
    public User infoMember(int mno) {
    	return luckvSqlSession.selectOne(NAME_SPACE+"infoMember", mno);
    };
    
    // 회원정보수정
    public boolean  updateMember(User user) {
		int count = luckvSqlSession.update(NAME_SPACE+"updateMember",user);
        return count > 0 ? true : false;
	};
    
	
	// 구독신청
	public boolean videoSubYn(User user) {
		int count = luckvSqlSession.update(NAME_SPACE+"videoSubYn",user);
        return count > 0 ? true : false;
	};
	
	// 구독해지
	public boolean videoSubNot(User user) {
		int count = luckvSqlSession.update(NAME_SPACE+"videoSubNot",user);
        return count > 0 ? true : false;
	};
	
	// 구독해지 자동변경
	public boolean videoSubAuto() {
		int count = luckvSqlSession.update(NAME_SPACE+"videoSubAuto" );
        return count > 0 ? true : false;
	};
}
