package com.application.usedallea.member.dao;

import org.apache.ibatis.annotations.Mapper;

import com.application.usedallea.member.dto.MemberDTO;

import java.util.List;

@Mapper
public interface MemberDAO {

	void registerMember(MemberDTO memberDTO);

	String dupleCheckId(String userId);

	MemberDTO getLoginData(String userId);

	MemberDTO getMemberDetail(String userId);

	void updateMember(MemberDTO memberDTO);

	void updateDeleteMember(String userId);

	int getAllUserCnt();

    List<MemberDTO> getMemberList();

	void removeUser(String userId);
}
