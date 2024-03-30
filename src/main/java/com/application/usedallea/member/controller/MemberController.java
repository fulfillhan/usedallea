package com.application.usedallea.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import com.application.usedallea.member.dto.MemberDTO;
import com.application.usedallea.member.service.MemberService;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/member")
public class MemberController {
	
		@Autowired
		private MemberService memberService;
		
		@GetMapping("/main")
		public String main() {
			return "common/main";
		}
		
		@GetMapping("/registerMember")
		public String registerMember () {
			return "member/register";
		}
		
		@PostMapping("/registerMember")
		@ResponseBody
		public String registerMember (@ModelAttribute MemberDTO memberDTO) {
			memberService.registerMember(memberDTO);
			
			String script = """
					<script>
					alert('중고올래의 회원이 되신것을 축하드립니다!');
					location.href='/member/main';
					</script>
					""";
			return script;
		}
		
		@PostMapping("/dupleCheckId")
		@ResponseBody
		public String dupleCheckId(@RequestParam("userId") String userId) {
			
			String validateId = "y";
			if(memberService.dupleCheckId(userId)) {
				validateId = "n";
			}
			return validateId;
		}
		
		@GetMapping("/login")
	     public String login() {
			return "member/login";
		}
		
		@PostMapping("/login")
		@ResponseBody
		public String login(@RequestBody MemberDTO memberDTO,HttpServletRequest request) {
			// 로그인이 되면 세션화 적용하기
			String validateLogin = memberService.login(memberDTO);
			if(validateLogin.equals("y")) { //로그인이 가능
				HttpSession session = request.getSession();
				session.setAttribute("userId", memberDTO.getUserId());
			}
			return validateLogin;
		}
		
		@GetMapping("/logout")
		public String logout (HttpServletRequest request) {
			HttpSession session = request.getSession();
			session.invalidate();
			
			return "redirect:/common/main";
		}
		
		
		

		
}
