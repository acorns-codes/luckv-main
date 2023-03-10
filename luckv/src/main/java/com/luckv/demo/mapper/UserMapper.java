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
	void  updateMember(user user);
    
}
