package com.application.usedallea.member.service;

import com.application.usedallea.member.dto.MemberDTO;

public interface MemberService {

	public void registerMember(MemberDTO memberDTO);

	public boolean dupleCheckId(String userId);

	public String login(MemberDTO memberDTO);

}
