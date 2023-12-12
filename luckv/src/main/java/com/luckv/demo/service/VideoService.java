package com.luckv.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.luckv.demo.dao.VideoDao;
import com.luckv.demo.vo.Video;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class VideoService {
	
	private final VideoDao videoDao;
	
	// 파일업로드
	public boolean videoUpload(Video video) {
		return videoDao.videoUpload(video);
	}

	
	// 파일다운로드
	public Video videoDownload(int ano) {			
		return videoDao.videoDownload(ano);
	}
	
	
	// 동영상 스트리밍
	public Video videoPlay(int ano) {			
		return videoDao.videoPlay(ano);
	}
	
}
