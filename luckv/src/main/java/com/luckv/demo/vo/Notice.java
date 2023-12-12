package com.luckv.demo.vo;

import java.sql.Date;

import lombok.Data;

@Data
public class Notice {
	
	private int no; // 공지사항 번호
	private int nid; // 공지사항 등록 회원번호
	private String mid; // 공지사항 등록 아이디
	private String title; // 제목
	private String content; // 내용
	private Date createAt; // 등록일
	private Date updateAt; // 수정일
	
	private int page;
	private int totalPageCnt;
	private int rowCnt;

}
