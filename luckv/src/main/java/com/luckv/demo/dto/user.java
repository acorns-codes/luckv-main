package com.luckv.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class user {
	private int mno;
    private String mid;
    private String pwd;
    private String name;
    private String ph;
    private String auth;
    private String acccount;
    private String birth_date;
}
