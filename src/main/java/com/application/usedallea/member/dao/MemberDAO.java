package com.application.usedallea.member.dao;

import org.apache.ibatis.annotations.Mapper;

import com.application.usedallea.member.dto.MemberDTO;

@Mapper
public interface MemberDAO {

	public void registerMember(MemberDTO memberDTO);

	public String dupleCheckId(String userId);
	
	public MemberDTO getLoginData(String userId);

}
