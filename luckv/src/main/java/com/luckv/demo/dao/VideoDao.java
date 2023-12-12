package com.luckv.demo.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.luckv.demo.vo.Video;


@Repository
public class VideoDao {
	
	private static final String NAME_SPACE = "VideoDao.VideoMapper.";

	@Autowired
	@Qualifier(value = "luckvSqlSession")
	private SqlSession luckvSqlSession;
	
		
	public boolean videoUpload(Video video) {
		int count = luckvSqlSession.insert(NAME_SPACE+"videoUpload",video);
        return count > 0 ? true : false;
	};
	
	public Video videoDownload(int ano) {
		return luckvSqlSession.selectOne(NAME_SPACE+"videoDownload",ano);
	};

	public Video videoPlay(int ano) {
		return luckvSqlSession.selectOne(NAME_SPACE+"videoPlay",ano);
	};
}
 