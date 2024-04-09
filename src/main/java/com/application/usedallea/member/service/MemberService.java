package com.application.usedallea.member.service;

import com.application.usedallea.member.dto.MemberDTO;

public interface MemberService {

	public void registerMember(MemberDTO memberDTO);

	public String dupleCheckId(String userId);

	public boolean login(MemberDTO memberDTO);

     public MemberDTO getMemberDetail(String userId);

	 public void updateMember(MemberDTO memberDTO);

	 public void updateDelete(String userId);
}
