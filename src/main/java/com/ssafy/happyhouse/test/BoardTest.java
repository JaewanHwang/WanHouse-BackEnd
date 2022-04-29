package com.ssafy.happyhouse.test;

import com.ssafy.happyhouse.model.dao.BoardDAO;
import com.ssafy.happyhouse.model.dto.Board;

import java.sql.SQLException;


public class BoardTest {
	static final String LONGLINE = "-------------------------------------------------------------------------------";

	public static void main(String[] args) throws SQLException {
		BoardDAO dao = new BoardDAO();
		
		System.out.println("2개의 게시글 등록");
		System.out.println(dao.insertBoard(new Board("안녕하세요", "만나서 반갑습니다", "assy110")));
		System.out.println(dao.insertBoard(new Board("안녕히 계세요", "저는 떠납니다",  "dyim0403")));
		System.out.println(LONGLINE);
		
		System.out.println("2개의 게시글 조회");
		System.out.println(dao.selectBoard(9));
		System.out.println(dao.selectBoard(10));
		System.out.println(LONGLINE);

		System.out.println("1개의 게시글 삭제");
		System.out.println(dao.deleteBoard(9));
		System.out.println(LONGLINE);
		
		System.out.println("삭제한 게시글 조회");
		System.out.println(dao.selectBoard(10));
		System.out.println(LONGLINE);
		
		System.out.println("삭제하지 않은 게시글 조회");
		System.out.println(dao.selectBoard(10));
		System.out.println(LONGLINE);
	}

}
