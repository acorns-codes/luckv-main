package com.luckv.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Attend {
	
	private int ano; //  경매 생성번호
	private int buyer; // 입찰자 회원번호
	private String buyerNm; // 입찰자 이름
	private int bidding; // 입찰가
	
}
