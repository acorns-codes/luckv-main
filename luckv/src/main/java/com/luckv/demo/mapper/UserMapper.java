package com.luckv.demo.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.luckv.demo.dto.user;

@Mapper
@Repository
public interface UserMapper {
	
	// 아이디 중복확인
	int getId(user user);
	
	// 회원가입
    int addMember(user user);
    
    // 로그인
    user login(user user);

	// 회원정보 조회
	user infoMember(int mno);
    
    // 회원정보수정
	int  updateMember(user user);
    
	
	// 구독신청
	int videoSubYn(user user);
	
	// 구독해지
	int videoSubNot(user user);
	
	// 구독해지 자동변경
	int videoSubAuto();
}
