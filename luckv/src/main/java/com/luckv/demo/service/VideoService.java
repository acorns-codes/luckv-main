package com.luckv.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.luckv.demo.dto.Question;
import com.luckv.demo.dto.Video;
import com.luckv.demo.mapper.VideoMapper;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class VideoService {
	
	private final VideoMapper videoMapper;
	
	// 파일업로드
	public boolean videoUpload(Video video) {
		int n = videoMapper.videoUpload(video);
        return n > 0;
	}

	
	// 파일다운로드
	public Video videoDownload(int ano) {			
	       return videoMapper.videoDownload(ano);
	}
	
	
	// 동영상 스트리밍
	public Video videoPlay(int ano) {			
	       return videoMapper.videoPlay(ano);
	}
	
}
