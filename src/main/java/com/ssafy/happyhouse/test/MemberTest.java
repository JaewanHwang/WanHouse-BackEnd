package com.ssafy.happyhouse.test;

import com.ssafy.happyhouse.model.dao.MemberDAO;
import com.ssafy.happyhouse.model.dto.Member;

import java.sql.SQLException;


public class MemberTest {
	static final String LONGLINE = "-------------------------------------------------------------------------";

	public static void main(String[] args) throws SQLException {
		MemberDAO dao = new MemberDAO();
		
		// 4명의 멤버 추가
//		System.out.println(dao.insertMember(new Member("dyim0403", "1234", "임도영", "서울시 금천구", "010-1234-5678")));
//		System.out.println(dao.insertMember(new Member("wansang93", "2345", "김완상", "경기도 김포시", "010-2345-6789")));
//		System.out.println(dao.insertMember(new Member("assy110", "3456", "윤여빈", "인천시 연수구", "010-3456-7890")));
		System.out.println(dao.insertMember(new Member("tmpUser", "0000", "김삭제", "삭제시 삭제구", "010-0000-0000")));
		System.out.println(LONGLINE);
		
		// 4명 조회
		System.out.println(dao.selectMember("dyim0403"));
		System.out.println(dao.selectMember("wansang93"));
		System.out.println(dao.selectMember("assy110"));
		System.out.println(dao.selectMember("tmpUser"));
		System.out.println(LONGLINE);
		
		// 1명 삭제
		System.out.println(dao.deleteMember("tmpUser"));
		System.out.println(LONGLINE);
		
		// 삭제한 사람 조회
		System.out.println(dao.selectMember("tmpUser"));
		System.out.println(LONGLINE);
		
		// 삭제하지 않은 사람 3명 조회
		System.out.println(dao.selectMember("dyim0403"));
		System.out.println(dao.selectMember("wansang93"));
		System.out.println(dao.selectMember("assy110"));
		System.out.println(LONGLINE);
	}

}
