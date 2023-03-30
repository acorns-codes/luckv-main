package com.luckv.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.luckv.demo.dto.Notice;

@Mapper
@Repository
public interface  NoticeMapper {
	
	// 공지사항 갯수
	int noticeCount(Notice notice);
	
	// 공지사항 페이지처리
	List<Notice> noticePage(Notice notice);
	
	// 공지사항 등록
	int insertNotice(Notice notice);
	
	// 공지사항 상세보기
	Notice noticeDetail(int nno);
	
	// 공지사항 수정
	int noticeUpdate(Notice notice);
	
	

}

