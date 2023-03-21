package com.luckv.demo.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class Auction {

	private int ano; //  경매 생성번호
	private String seller; // 판매자 회원번호
	private String sellerNm; // 판매자 이름
	private String title; // 제목
	private String content; // 내용
	private int payStart; // 경메 시작가
	private int payMax; // 경매 최고가
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",  timezone = "Asia/Seoul")
	private LocalDateTime  startDay; // 경매 시작일
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",  timezone = "Asia/Seoul")
	private LocalDateTime  lastDay; // 경매 종료일
	
	private String vcate; // 경매 카테고리 
	private String status; // 판매상태
	private String buyer; // 경매낙찰자 회원번호
	private String buyerNm; // 경매낙찰자 이름

	
	
	
	// 경매 페이징처리
	private int page;   // 페이징을 갱신하기 위해 필요함.
    private int start; // 게시글 시작 번호
    private int end; // 한 페이지당 보여줄 게시글의 갯수
}
