package com.application.usedallea.member.dao;

import org.apache.ibatis.annotations.Mapper;

import com.application.usedallea.member.dto.MemberDTO;

@Mapper
public interface MemberDAO {

	public void registerMember(MemberDTO memberDTO);

	public String dupleCheckId(String userId);

	public MemberDTO getLoginData(String userId);

     public MemberDTO getMemberDetail(String userId);

     public void updateMember(MemberDTO memberDTO);

	public void updateDeleteMember(String userId);
}
