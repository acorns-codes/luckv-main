package com.luckv.demo.vo;

import java.sql.Date;

import lombok.Data;

@Data
public class Question {
	private int no; // QnA 번호
	private int qid; // QnA 등록 회원번호
	private String id; // QnA 등록 아이디
	private String title; // 제목
	private String content; // 내용
	private Date qCreate; // 등록일
	private Date qUpdate; // 수정일
	private int reply; // 댓글갯수
	private String qpwd; // 게시글 비밀번호
	
    // QnA 댓글
    private int ano; // QnA 답변번호
    private int aid; // QnA 답변자 회원번호
    private String ansid; // QnA 답변자 아이디
    private String comment; // 댓글
	private Date aCreate; // 댓글 등록일
	private Date aUpdate; // 댓글 수정일
    
	 private int page;
	 private int totalPageCnt;
	 private int rowCnt;
    
}
