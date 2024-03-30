package com.application.usedallea.member.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.application.usedallea.member.dao.MemberDAO;
import com.application.usedallea.member.dto.MemberDTO;

@Service
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private MemberDAO memberDAO;
	
	private static Logger logger = LoggerFactory.getLogger(MemberServiceImpl.class);

	@Override
	public void registerMember(MemberDTO memberDTO) {
		
		if(memberDTO.getSmsstsYn() == null) memberDTO.setSmsstsYn("n");
		if(memberDTO.getEmailstsYn() == null) memberDTO.setEmailstsYn("n");
		if(memberDTO.getPersonalInfoYn() == null) memberDTO.setPersonalInfoYn("n");
		
		memberDTO.setPassword(passwordEncoder.encode(memberDTO.getPassword()));
		
		memberDAO.registerMember(memberDTO);
	}

	@Override
	public boolean dupleCheckId(String userId) {
		boolean isDupleId = false;
		if(memberDAO.dupleCheckId(userId) != null) {
			isDupleId = true;
		}
		return isDupleId;
	}

	@Override
	public String login(MemberDTO memberDTO) {
		String loginPossible = "n";
		//입력한 아이디의 패스워드, 활성여부 확인하여 있으면 로그인가능
		MemberDTO loginData = memberDAO.getLoginData(memberDTO.getUserId());
		if(loginData != null) {
			if(passwordEncoder.matches(memberDTO.getPassword(), loginData.getPassword()) && loginData.getActiveYn().equals("y")) {
				loginPossible = "y";
			}
		}
		return loginPossible;
	}

	

}
