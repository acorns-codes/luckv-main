package com.luckv.demo.dto;

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
//	private String qpwd; //비밀번호
	// QnA 페이징처리
	private int page;   // 페이징을 갱신하기 위해 필요함.
    private int start; // 게시글 시작 번호
    private int end; // 한 페이지당 보여줄 게시글의 갯수
    
    
    // QnA 댓글
    private int ano; // QnA 답변번호
    private int aid; // QnA 답변자 회원번호
    private String ansid; // QnA 답변자 아이디
    private String comment; // 댓글
	private Date aCreate; // 댓글 등록일
	private Date aUpdate; // 댓글 수정일
    
    
    
}
