package com.luckv.demo.dto;

import java.sql.Date;

import lombok.Data;

@Data
public class Video {
	
	private int ano; // 경매번호
    private String videoName; // 동영상 이름
    private String videoFile; // 동영상 파일
    
    
}
