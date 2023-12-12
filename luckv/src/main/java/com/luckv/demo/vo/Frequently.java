package com.luckv.demo.vo;

import java.sql.Date;

import lombok.Data;

@Data
public class Frequently {

	private int fno; // FaQ 번호
	private int fid; // FaQ 등록 회원번호
	private String mid; // FaQ 등록 아이디
	private String questions; // 자주묻는질문
	private String asked; // 답변
	private Date fCreate; // 등록일
	private Date fUpdate; // 수정일
	private String category; // 카테고리
	
	  private int page;
	  private int totalPageCnt;
	  private int rowCnt;
}
