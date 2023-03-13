package com.luckv.demo.dto;

import java.sql.Date;

import lombok.Data;

@Data
public class Notice {
	
	private int nno; // 공지사항 번호
	private int nid; // 공지사항 등록 회원번호
	private String mid; // 공지사항 등록 아이디
	private String title; // 제목
	private String content; // 내용
	private Date createAt; // 등록일
	private Date updateAt; // 수정일
	
	// 공지사항 페이징처리
	private int page;   // 페이징을 갱신하기 위해 필요함.
    private int start; // 게시글 시작 번호
    private int end; // 한 페이지당 보여줄 게시글의 갯수

}
