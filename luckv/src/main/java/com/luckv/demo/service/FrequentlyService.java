package com.luckv.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.luckv.demo.dao.FrequentlyDao;
import com.luckv.demo.vo.Frequently;

import lombok.RequiredArgsConstructor;


@Service
@Transactional
@RequiredArgsConstructor
public class FrequentlyService {

private final FrequentlyDao frequentlyDao;
	

	// FaQ 페이징처리
	public List<Frequently> frequentlyList(Frequently frequently) {
        return frequentlyDao.frequentlyList(frequently);
    }
		
	
	// FaQ 등록
	public boolean insertFrequently(Frequently frequently) {
		 return frequentlyDao.insertFrequently(frequently);
	}
		
	// FaQ 상세
	public Frequently frequentlyDetail(int fno) {
		return frequentlyDao.frequentlyDetail(fno);
	}
	
	// FaQ 수정
	public boolean frequentlyUpdate(Frequently frequently) {
		return frequentlyDao.frequentlyUpdate(frequently);
    }
	
	// FaQ 삭제
	public boolean frequentlyDelete(int fno) {
		return frequentlyDao.frequentlyDelete(fno);
    }
	
}
