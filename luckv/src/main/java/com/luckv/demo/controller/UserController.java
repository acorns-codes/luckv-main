package com.luckv.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.luckv.demo.dto.user;
import com.luckv.demo.response.DefaultRes;
import com.luckv.demo.response.ResponseMessage;
import com.luckv.demo.response.StatusCode;
import com.luckv.demo.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequiredArgsConstructor
public class UserController {

	 public final Logger logger = LoggerFactory.getLogger(UserController.class);

	    private final UserService service;

	    // 아이디 중복확인
	    @PostMapping("/getId")
	    public ResponseEntity<String> getId(@RequestBody user user) {
	        boolean b = service.getId(user);
	        if(b) {	            
	        	return  new ResponseEntity(DefaultRes.res(StatusCode.BAD_REQUEST, ResponseMessage.ID_UNAVAILABLE, b), HttpStatus.OK);
	        }
	        return  new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.ID_AVAILABLE, b), HttpStatus.OK);
	    }

	    // 회원가입
	    @PostMapping("/addMember")
	    public ResponseEntity<String> addMember(@RequestBody user user) {
	        boolean b = service.addMember(user);

	        if(b) {
	            return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.CREATED_USER, b), HttpStatus.OK);
	        }
	        return  new ResponseEntity(DefaultRes.res(StatusCode.BAD_REQUEST, ResponseMessage.NOT_CREATED_USER, b), HttpStatus.OK);
	    }

	    
	    @PostMapping("login")
	    public ResponseEntity login(@RequestBody user user) {  	
	        return service.login(user) == null?  
	        		new ResponseEntity(DefaultRes.res(StatusCode.UNAUTHORIZED, ResponseMessage.NOT_FOUND_USER, service.login(user)), HttpStatus.OK) :
	        		new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.LOGIN_SUCCESS, service.login(user)), HttpStatus.OK);
	    }
	    
	    
	    // 회원정보 조회
	    @PostMapping("/infoMember")
	    public ResponseEntity infoMember(int mno) {
	      return service.infoMember(mno) == null?  
	        		new ResponseEntity(DefaultRes.res(StatusCode.NO_CONTENT, ResponseMessage.NOT_FOUND_USER, service.infoMember(mno)), HttpStatus.OK)
	        		: new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.READ_USER, service.infoMember(mno)), HttpStatus.OK);
	    }
	    
	    // 회원정보 수정	    
	    @PostMapping("/updateMember")
	    public ResponseEntity<String> updateMember(@RequestBody user user) {
	    	
	    	boolean b = service.updateMember(user);
	        if(b) {
	            return  new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.UPDATE_USER, b), HttpStatus.OK);
	        }
	        return  new ResponseEntity(DefaultRes.res(StatusCode.BAD_REQUEST, ResponseMessage.NOT_UPDATE_USER, b), HttpStatus.OK);
	    }
	    
	    
	  	// 동영상 구독 신청
	  	@PostMapping("/videoSubYn")
	    public ResponseEntity videoSubYn(@RequestBody user user) {
	        
	        boolean b = service.videoSubYn(user);
	        
	        if(b) {
	            return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.CREATED_SUB, b), HttpStatus.OK);
	        }
	        return  new ResponseEntity(DefaultRes.res(StatusCode.BAD_REQUEST, ResponseMessage.NOT_CREATED_SUB, b), HttpStatus.OK);
	    }
	  	
	 // 동영상 구독 해지
	  	@PostMapping("videoSubNot")
	    public ResponseEntity videoSubNot(@RequestBody user user) {  	        
	        boolean b = service.videoSubNot(user);
			  if(b) {
		            return  new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.DELETE_SUB, b), HttpStatus.OK);
		        }
		        return  new ResponseEntity(DefaultRes.res(StatusCode.BAD_REQUEST, ResponseMessage.NOT_DELETE_SUB, b), HttpStatus.OK);
	    }
	    
}
