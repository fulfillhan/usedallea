package com.application.usedallea.member.service;

import com.application.usedallea.member.dto.MemberDTO;

public interface MemberService {

	void registerMember(MemberDTO memberDTO);

	String dupleCheckId(String userId);

	boolean login(MemberDTO memberDTO);

	MemberDTO getMemberDetail(String userId);

	void updateMember(MemberDTO memberDTO);

	void updateDelete(String userId);
}
