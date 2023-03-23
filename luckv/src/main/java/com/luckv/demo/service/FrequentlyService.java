package com.luckv.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.luckv.demo.dto.Frequently;
import com.luckv.demo.mapper.FrequentlyMapper;

import lombok.RequiredArgsConstructor;


@Service
@Transactional
@RequiredArgsConstructor
public class FrequentlyService {

private final FrequentlyMapper frequentlyMapper;
	
	// FaQ 목록
	public List<Frequently> frequentlyList(Frequently frequently) {
		return frequentlyMapper.frequentlyList(frequently);
	}
	
	// FaQ 갯수
	public int frequentlyCount(Frequently frequently) {
        return frequentlyMapper.frequentlyCount(frequently);
    }
	// FaQ 페이징처리
	public List<Frequently> frequentlyPage(Frequently frequently) {
        return frequentlyMapper.frequentlyPage(frequently);
    }
		
	
	// FaQ 등록
	public boolean insertFrequently(Frequently frequently) {
		 int n = frequentlyMapper.insertFrequently(frequently);
	        return n > 0;
	}
		
	// FaQ 상세
	public Frequently frequentlyDetail(int fno) {
		return frequentlyMapper.frequentlyDetail(fno);
	}
	
	// FaQ 수정
	public boolean frequentlyUpdate(Frequently frequently) {
		int n = frequentlyMapper.frequentlyUpdate(frequently);
        return n > 0;		
    }
	
	// FaQ 삭제
		public boolean frequentlyDelete(int fno) {
			int n = frequentlyMapper.frequentlyDelete(fno);
	        return n > 0;
	    }
	
}
