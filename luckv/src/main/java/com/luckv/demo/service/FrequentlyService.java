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

private final FrequentlyMapper frequentlyMapper;;
	
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
	public void insertFrequently(Frequently frequently) {
		frequentlyMapper.insertFrequently(frequently);
	}
		
	// FaQ 상세
//	public Frequently frequentlyDetail(int fno) {
//		return frequentlyMapper.frequentlyDetail(fno);
//	}
	
	// FaQ 수정
	public void frequentlyUpdate(Frequently frequently) {
		frequentlyMapper.frequentlyUpdate(frequently);
    }
	
	// FaQ 수정
		public void frequentlyDelete(int fno) {
			frequentlyMapper.frequentlyDelete(fno);
	    }
	
}
