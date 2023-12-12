package com.luckv.demo.vo;

import java.sql.Date;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;


@Data
public class User {
	
	private int mno; // 유저 번호
    private String mid; // 유저 아이디
    private String pwd; // 비밀번호
    private String name; // 이름
    private String ph; // 휴대폰 번호
    private String auth; // 권한
    private String acccount; // 계좌번호
    private Date birthDate; // 생년월일 
    private String subYn; // 구독여부
   	private String  subStartDay; // 구독 시작일
	private String  subLastDay; // 구독 종료일
}
