package com.luckv.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.luckv.demo.dto.user;
import com.luckv.demo.mapper.UserMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
	 private final UserMapper userMapper;

	    @Override
	    public List<user> getUserList() {
	        return userMapper.getUserList();

	    }
}
