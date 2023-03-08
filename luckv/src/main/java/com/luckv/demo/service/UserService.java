package com.luckv.demo.service;

import java.util.List;

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

    public boolean getId(user user) {
        int n = userMapper.getId(user);
        return n > 0;
    }

    public boolean addMember(user user) {
        int n = userMapper.addMember(user);
        return n > 0;
    }

    public user login(user user) {
        return userMapper.login(user);
    }

	public user updateMember(user user) {
	     return userMapper.updateMember(user);
	}

}
