package com.luckv.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.luckv.demo.dto.user;
import com.luckv.demo.mapper.UserMapper;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
	
	private final UserMapper userMapper;

	// 아이디 중복확인
    public boolean getId(user user) {
        int n = userMapper.getId(user);
        return n > 0;
    }

    // 회원가입
    public boolean addMember(user user) {
        int n = userMapper.addMember(user);
        return n > 0;
    }

    // 로그인
    public user login(user user) {
        return userMapper.login(user);
    }
    
    // 회원정보 조회
    public user infoMember(int mno) {
        return userMapper.infoMember(mno);
    }

    // 회원정보 수정
	public boolean updateMember(user user) {
		int n = userMapper.updateMember(user);
        return n > 0;
	}

	
}
