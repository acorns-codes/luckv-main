package com.luckv.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.luckv.demo.dto.Frequently;



@Mapper
@Repository
public interface FrequentlyMapper {

	// FaQ 목록
	List<Frequently> frequentlyList(Frequently frequently);

	// FaQ 갯수
	int frequentlyCount(Frequently frequently);
	
	// FaQ 페이지처리
	List<Frequently> frequentlyPage(Frequently frequently);
	
	// FaQ 등록
	int insertFrequently(Frequently frequently);
	
	// FaQ 상세보기
	Frequently frequentlyDetail(int fno);
	
	// FaQ 수정
	int frequentlyUpdate(Frequently frequently);
	
	// FaQ 삭제
	int frequentlyDelete(int fno);
}
