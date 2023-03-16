package com.luckv.demo.response;

public class StatusCode {
	
	public static final int OK = 200; // 성공
    public static final int CREATED = 201; // 데이터 생성,수정 성공 
    public static final int NO_CONTENT = 204; // 조회 데이터 없음
    public static final int BAD_REQUEST =  400; // 클라이언트 잘못된 요청
    public static final int UNAUTHORIZED = 401; // 회원 인증 실패
    public static final int FORBIDDEN = 403; // 권한 없음
    public static final int NOT_FOUND = 404; // 페이지를 찾을수 없음
    public static final int INTERNAL_SERVER_ERROR = 500; // 서버오류
    public static final int SERVICE_UNAVAILABLE = 503; // 서버 현재사용 불가
    public static final int DB_ERROR = 600; // DB오류 
    
}
                                                         