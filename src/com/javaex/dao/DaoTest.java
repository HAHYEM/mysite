package com.javaex.dao;

import com.javaex.vo.UserVo;

public class DaoTest {
	public static void main(String[] args) {
		
		UserVo uvo = new UserVo();
		uvo.setName("ÀÌ½Â±â");
		uvo.setEmail("**@gmail.com");
		uvo.setPassword("012");
		uvo.setGender("male");

		UserDao udao = new UserDao();
		udao.insert(uvo);
		
	}
}
