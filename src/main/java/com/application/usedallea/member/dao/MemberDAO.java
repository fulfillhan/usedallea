package com.application.usedallea.member.dao;

import org.apache.ibatis.annotations.Mapper;

import com.application.usedallea.member.dto.MemberDTO;

@Mapper
public interface MemberDAO {

	void registerMember(MemberDTO memberDTO);

	String dupleCheckId(String userId);

	MemberDTO getLoginData(String userId);

	MemberDTO getMemberDetail(String userId);

	void updateMember(MemberDTO memberDTO);

	void updateDeleteMember(String userId);
}
