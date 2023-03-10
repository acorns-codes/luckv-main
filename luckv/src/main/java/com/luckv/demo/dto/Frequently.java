package com.luckv.demo.dto;

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
	
	// FaQ 페이징처리
	private int page;   // 페이징을 갱신하기 위해 필요함.
    private int start; // 게시글 시작 번호
    private int end; // 한 페이지당 보여줄 게시글의 갯수
}
