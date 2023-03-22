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
import com.luckv.demo.mapper.AttendMapper;
import com.luckv.demo.mapper.FrequentlyMapper;

import lombok.RequiredArgsConstructor;

@Service
@ServerEndpoint(value="/auctionDetail")
public class AttendService {
	private static  AttendMapper  attendMapper;
	private static Set<Session> clients = 
			Collections.synchronizedSet(new HashSet<Session>());

	
	@OnOpen
	public void onOpen(Session s) {
		System.out.println("open session : " + s.toString());
		if(!clients.contains(s)) {
			clients.add(s);
			System.out.println("session open : " + s);
		}else {
			System.out.println("이미 연결된 session 임!!!");
		}
	}
	
	
	@OnMessage
	public void onMessage(Attend attend, Session session) throws Exception{
		System.out.println("receive message : " + attend);
		for(Session s : clients) {
			System.out.println("send data : " + attend);
			attendMapper.insertAttend(attend);
			s.getBasicRemote().sendObject(attend);
			
		}
		
	}
	
	@OnClose
	public void onClose(Session s) {
		System.out.println("session close : " + s);
		clients.remove(s);
	}
}
