package com.luckv.demo.service;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.luckv.demo.dto.Attend;
import com.luckv.demo.dto.Auction;
import com.luckv.demo.mapper.AttendMapper;
import com.luckv.demo.mapper.FrequentlyMapper;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class AttendService {
	
	private final  AttendMapper  attendMapper;
	
	// 입찰 등록
	public boolean insertAttend(Attend attend) {
		return attendMapper.insertAttend(attend);
        
	}
	
}
