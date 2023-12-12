package com.luckv.demo.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.luckv.demo.service.UserService;
import com.luckv.demo.vo.User;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UserController {
	    private final UserService service;

	    // 아이디 중복확인
	    @PostMapping("/user/Idcheck")
	    public Map<String,Object> getId(@RequestBody User user) {
	    	Map<String,Object> obj = new HashMap<>();    
	        
	        if(service.getId(user)) {
	        	obj.put("res","OK");
	        	obj.put("msg","ID_UNAVAILABLE");
	        	return obj;
	        }
	        obj.put("res","OK");
        	obj.put("msg","SUCCESS");
        	return obj;
	      
	    }

	    // 회원가입
	    @PostMapping("/user/join")
	    public Map<String,Object> addMember(@RequestBody User user) {
	    	Map<String,Object> obj = new HashMap<>();    

	        if(!service.addMember(user)) {
	        	obj.put("res","OK");
	        	obj.put("msg","NOT_CREATED_USER");
	        	return obj;
	        }
	        obj.put("res","OK");
        	obj.put("msg","SUCCESS");
        	return obj;
	       
	    }

	    
	    @PostMapping("/user/login")
	    public Map<String,Object> login(@RequestBody User user) {  	
	    	Map<String,Object> obj = new HashMap<>();
	    	User login = service.login(user);
	    	if(login == null) {
	        	obj.put("res","OK");
	        	obj.put("msg","NOT_FOUND_USER");
	        	return obj;
	        }
	        obj.put("res","OK");
        	obj.put("msg","SUCCESS");
        	obj.put("data",login);
        	return obj;
	        
	    }
	    
	    
	    // 회원정보 조회
	    @GetMapping("/user/info")
	    public Map<String,Object> infoMember(int mno) {
	    	Map<String,Object> obj = new HashMap<>();    
	    	User userInfo =  service.infoMember(mno);
	    	
			if(userInfo == null) {
	        	obj.put("res","OK");
	        	obj.put("msg","NOT_FOUND_USER");
	        	return obj;
	        }
	        obj.put("res","OK");
        	obj.put("msg","SUCCESS");
        	obj.put("data", userInfo);
        	return obj;
	        
	    }
	    
	    // 회원정보 수정	    
	    @PostMapping("/user/edit")
	    public Map<String,Object> updateMember(@RequestBody User user) {
	    	Map<String,Object> obj = new HashMap<>();    
	    	
	    	if(!service.updateMember(user)) {
	        	obj.put("res","OK");
	        	obj.put("msg","NOT_UPDATE_USER");
	        	return obj;
	        }
	        obj.put("res","OK");
        	obj.put("msg","SUCCESS");
        	return obj;
	       
	    }
	    
	    
	  	// 동영상 구독 신청
	  	@PostMapping("/user/video/sub")
	    public Map<String,Object> videoSubYn(@RequestBody User user) {
	  		Map<String,Object> obj = new HashMap<>();    
	        
	        if(!service.videoSubYn(user)) {
	        	obj.put("res","OK");
	        	obj.put("msg","NOT_CREATED_SUB");
	        	return obj;
	        }
	        obj.put("res","OK");
        	obj.put("msg","SUCCESS");
        	return obj;
	       
	    }
	  	
	 // 동영상 구독 해지
	  	@PostMapping("/user/video/subnot")
	    public Map<String,Object> videoSubNot(@RequestBody User user) {  	        
	  		Map<String,Object> obj = new HashMap<>();    
	        
	        if(!service.videoSubNot(user)) {
	        	obj.put("res","OK");
	        	obj.put("msg","NOT_DELETE_SUB");
	        	return obj;
	        }
	        obj.put("res","OK");
        	obj.put("msg","SUCCESS");
        	return obj;
			  
	    }
	    
}
