package com.luckv.demo.dto;

import java.sql.Date;

import lombok.Data;


@Data
public class user {
	
	private int mno;
    private String mid;
    private String pwd;
    private String name;
    private String ph;
    private String auth;
    private String acccount;
    private Date birthDate;
    
}
