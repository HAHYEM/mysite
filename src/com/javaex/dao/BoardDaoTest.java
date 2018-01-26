package com.javaex.dao;

import java.util.List;

import com.javaex.vo.BoardVo;

public class BoardDaoTest {

	public static void main(String[] args) {
		BoardDao dao = new BoardDao();
		List<BoardVo> bList = dao.getList();
		
		for(BoardVo bvo : bList) {
			System.out.println(bvo.toString());
		}	
	}
	
}

