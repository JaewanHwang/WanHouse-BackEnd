package com.ssafy.happyhouse.model.mapper;

import com.ssafy.happyhouse.model.dto.MemberDto;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;


@Mapper
public interface MemberMapper {

	MemberDto selectMember(String memberId) throws SQLException;

	boolean insertMember(MemberDto member) throws SQLException;
	boolean deleteMember(String memberId) throws SQLException;
	
	boolean updateMember(MemberDto member) throws SQLException;
	
	

}