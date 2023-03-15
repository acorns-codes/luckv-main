package com.luckv.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luckv.demo.dto.user;
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
	    public String getId(user user) {
	        logger.info("MemberController getId()");
	        boolean b = service.getId(user);
	        if(b) {
	            return "아이디 사용가능";
	        }
	        return "아이디 중복";

	    }

	    // 회원가입
	    @PostMapping("/addMember")
	    public String addMember(user user) {
	        logger.info("MemberController addMember()");
	        boolean b = service.addMember(user);

	        logger.info(user.toString());

	        if(b) {
	            return "회원가입 완료";
	        }

	        return "회원가입 불가";
	    }

	    // 로그인
	    @PostMapping("/login")
	    public user login(user user) {
	        logger.info("MemberController login()");
	        return service.login(user);
	    }
	    
	    
	    // 회원정보 조회
	    @GetMapping("/infoMember")
	    public user infoMember(int mno) {
	      return service.infoMember(mno);
	    }
	    
	    // 회원정보 수정	    
	    @GetMapping("/updateMember")
	    public void updateMember(user user) {
	    	service.updateMember(user);
	    }
}
