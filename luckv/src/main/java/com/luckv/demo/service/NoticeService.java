package com.luckv.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.luckv.demo.dao.NoticeDao;
import com.luckv.demo.vo.Notice;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class NoticeService {

	private final NoticeDao noticeDao;;
	

	// 공지사항 페이징처리
	public List<Notice> noticeList(Notice notice) {
        return noticeDao.noticeList(notice);
    }
		
	
	// 공지사항 등록
	public boolean insertNotice(Notice notice) {
        return noticeDao.insertNotice(notice);
	}
		
	// 공지사항 상세
	public Notice noticeDetail(int nno) {
		return noticeDao.noticeDetail(nno);
	}
	
	// 공지사항 수정
	public boolean noticeUpdate(Notice notice) {
		return noticeDao.noticeUpdate(notice);		
    }
	

}
