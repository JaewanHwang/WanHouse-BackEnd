package com.ssafy.happyhouse.model.service;

import com.ssafy.happyhouse.model.dto.MemberDto;
import com.ssafy.happyhouse.model.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class MemberService {
	MemberMapper memberMapper;
	@Autowired
	public void setMemberMapper(MemberMapper memberMapper) {
		this.memberMapper = memberMapper;
	}

	public MemberDto login(String id, String pw) throws SQLException {
		MemberDto m = getMember(id);
		if (m != null && pw.equals(m.getPw())) {
			return m;
		}
		return null;
	}

	public MemberDto getMember(String id) throws SQLException {
		return memberMapper.selectMember(id);
	}

	public boolean register(MemberDto mem) throws SQLException {
		if (getMember(mem.getId()) != null) {
			return false;
		}
		return memberMapper.insertMember(mem);
	}

	public boolean update(MemberDto mem) throws SQLException {
		return memberMapper.updateMember(mem);
	}

	public boolean delete(String memberId) throws SQLException {
		return memberMapper.deleteMember(memberId);
	}
}
