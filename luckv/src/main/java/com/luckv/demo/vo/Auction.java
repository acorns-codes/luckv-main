package com.luckv.demo.vo;

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
	private String  startDay; // 경매 시작일
	private String  lastDay; // 경매 종료일
	private int  bidding; // 입찰가
	private String vcate; // 경매 카테고리 
	private String status; // 판매상태
	private int  buyer; // 경매낙찰자 회원번호
	private String buyerNm; // 경매낙찰자 이름
	private String kind; // 동영상 형태 (구독,무료, 경매)
	private String regDt;
	private String udtDt;
	private int sort;
	
	private int page;
    private int totalPageCnt;
    private int rowCnt;

}
