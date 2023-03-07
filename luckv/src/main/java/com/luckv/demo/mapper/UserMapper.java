package com.luckv.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.luckv.demo.dto.user;

@Mapper
@Repository
public interface UserMapper {
	List<user> getUserList();
}
