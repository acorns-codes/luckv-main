package com.luckv.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.luckv.demo.dto.user;
import com.luckv.demo.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequiredArgsConstructor
public class UserController {
	 private final UserService userService;
	    
	 	
	    @GetMapping("/hello")
	    public String hello() {
	        return "Hello World";
	    }

	    @GetMapping("/user")
	    public List<user> user() {
	        return userService.getUserList();
	    }
}
