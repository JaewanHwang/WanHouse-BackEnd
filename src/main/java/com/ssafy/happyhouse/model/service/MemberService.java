package com.ssafy.happyhouse.model.service;

import java.sql.SQLException;

import com.ssafy.happyhouse.model.dao.MemberDAO;
import com.ssafy.happyhouse.model.dto.Member;


public class MemberService {
	MemberDAO memberdao = new MemberDAO();

	public Member login(String id, String pw) throws SQLException {
		Member m = getMember(id);
		if (m != null && pw.equals(m.getPw())) {
			return m;
		}

		return null;
	}

	public Member getMember(String id) throws SQLException {
		return memberdao.selectMember(id);
	}

	public boolean register(Member mem) throws SQLException {
		if (getMember(mem.getId()) != null) {
			return false;
		}

		return memberdao.insertMember(mem);
	}

	public boolean update(Member mem) throws SQLException {
		return memberdao.updateMember(mem);
	}

	public boolean delete(String memberId) throws SQLException {
		return memberdao.deleteMember(memberId);

	}

}
