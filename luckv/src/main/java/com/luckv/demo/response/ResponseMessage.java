package com.luckv.demo.response;

public class ResponseMessage {
	
	public static final String LOGIN_SUCCESS = "로그인 성공";
    public static final String LOGIN_FAIL = "로그인 실패";
    public static final String READ_USER = "회원 정보 조회 성공";
    public static final String NOT_FOUND_USER = "회원을 찾을 수 없습니다.";
    public static final String CREATED_USER = "회원 가입 성공";
    public static final String NOT_CREATED_USER = "회원 가입 실패";
    public static final String UPDATE_USER = "회원 정보 수정 성공";
    public static final String NOT_UPDATE_USER = "회원 정보 수정 실패";
    public static final String DELETE_USER = "회원 탈퇴 성공";
    public static final String INTERNAL_SERVER_ERROR = "서버 내부 에러";
    public static final String DB_ERROR = "데이터베이스 에러";
    public static final String ID_UNAVAILABLE = "아이디 중복";
    public static final String ID_AVAILABLE = "아이디 사용가능";
    
    
    
    public static final String READ_BOARD = "게시판조회 성공";
    public static final String NOT_READ_BOARD = "게시판조회 실패";
    public static final String CREATED_BOARD = "게시글등록 성공";
    public static final String NOT_CREATED_BOARD = "게시글등록 실패";
    public static final String UPDATE_BOARD = "게시글수정 성공";
    public static final String NOT_UPDATE_BOARD = "게시글수정 실패";
    public static final String DELETE_BOARD = "게시글삭제 성공";
    public static final String NOT_DELETE_BOARD = "게시글삭제 실패";

    public static final String CREATED_VIDEO = "동영상등록 성공";
    public static final String NOT_CREATED_VIDEO = "동영상등록 실패";
    public static final String DOWNLOAD_VIDEO = "동영상다운로드 성공";
    public static final String NOT_DOWNLOAD_VIDEO = "동영상다운로드 실패";

    public static final String CHANGE_AUCTION= "경매 -> 구독으로 변경 성공";
    public static final String NOT_CHANGE_AUCTION = "경매 -> 구독으로 변경 실패";
    
    public static final String CREATED_SUB = "동영상 구독 성공";
    public static final String NOT_CREATED_SUB = "동영상 구독 실패";
    public static final String DELETE_SUB = "동영상 구독해지 성공";
    public static final String NOT_DELETE_SUB = "동영상 구독해지 실패";
}
