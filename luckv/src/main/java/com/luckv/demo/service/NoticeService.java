package com.luckv.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.luckv.demo.dto.Notice;
import com.luckv.demo.mapper.NoticeMapper;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class NoticeService {

	private final NoticeMapper noticeMapper;;
	
	// 공지사항 조회
	public List<Notice> noticeList() {
		return noticeMapper.noticeList();
	}
	
	// 공지사항 갯수
	public int noticeCount(Notice notice) {
        return noticeMapper.noticeCount(notice);
    }
	// 공지사항 페이징처리
	public List<Notice> noticePage(Notice notice) {
        return noticeMapper.noticePage(notice);
    }
		
	
	// 공지사항 등록
	public boolean insertNotice(Notice notice) {
        int n = noticeMapper.insertNotice(notice);
        return n > 0;
	}
		
	// 공지사항 상세
	public Notice noticeDetail(int nno) {
		return noticeMapper.noticeDetail(nno);
	}
	
	// 공지사항 수정
	public boolean noticeUpdate(Notice notice) {
		int n = noticeMapper.noticeUpdate(notice);
        return n > 0;
		
    }
	

}
