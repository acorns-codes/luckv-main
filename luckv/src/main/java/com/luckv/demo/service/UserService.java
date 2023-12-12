package com.luckv.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.luckv.demo.dao.UserDao;
import com.luckv.demo.vo.User;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
	
	private final UserDao userDao;

	// 아이디 중복확인
    public boolean getId(User user) {
        return userDao.getId(user);
    }

    // 회원가입
    public boolean addMember(User user) {
        return userDao.addMember(user);
    }

    // 로그인
    public User login(User user) {
        return userDao.login(user);
    }
    
    // 회원정보 조회
    public User infoMember(int mno) {
        return userDao.infoMember(mno);
    }

    // 회원정보 수정
	public boolean updateMember(User user) {
		return userDao.updateMember(user);
	}
	
	// 동영상 구독 신청
	public boolean videoSubYn(User user) {
		return userDao.videoSubYn(user);
	}
	
	// 동영상 구독 신청
	public boolean videoSubNot(User user) {
		return userDao.videoSubNot(user);
	}
	
	public boolean videoSubAuto() {
		return userDao.videoSubAuto();
	}
		

	
}
