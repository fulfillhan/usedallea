package com.application.usedallea.member.controller;

import com.fasterxml.jackson.annotation.JsonCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

	private String getUserId(HttpServletRequest request) {
		HttpSession session = request.getSession();
		return (String) session.getAttribute("userId");
	}

//	@GetMapping("/register")
//	public String registerMember() {
//		return "member/registerOrUpdate";
//	}

//	@PostMapping("/register")
//	@ResponseBody
//	public String registerMember(@ModelAttribute MemberDTO memberDTO) {
//		memberService.registerMember(memberDTO);
//
//		String script = """
//				<script>
//				alert('중고올래의 회원이 되신것을 축하드립니다!');
//				location.href='/usedallea/main';
//				</script>
//				""";
//		return script;
//	}

	@GetMapping("/registerOrUpdate")
	public String registerOrUpdate(HttpServletRequest request, Model model) {

		 String userId = getUserId(request);

		//userId가 있으면 수정 페이지로
		if ( userId != null) {
			MemberDTO memberDetail = memberService.getMemberDetail(userId);
			if (memberDetail == null) {
				memberDetail = new MemberDTO();
			}

			model.addAttribute("memberDTO",memberDetail);

		} else{
			model.addAttribute("memberDTO", new MemberDTO());

		}
		return "/member/registerOrUpdate";
	}


	@PostMapping("/registerOrUpdate")
	public String registerOrUpdate(@ModelAttribute MemberDTO memberDTO, HttpServletRequest request){
		String userId = getUserId(request);

		if(userId == null){
			// 회원가입으로 로직 처리
			memberService.registerMember(memberDTO);
		}
		else{
			//회원 수정으로 로직 처리
			memberService.updateMember(memberDTO);

		}
		return "redirect:/usedallea/main";
	}

	@PostMapping("/dupleCheckId")
	@ResponseBody
	public String dupleCheckId(@RequestParam("userId") String userId) {

		return memberService.dupleCheckId(userId);
	}

	@GetMapping("/login")
	public String login() {
		return "member/login";
	}

	@PostMapping("/login")
	@ResponseBody
	public String login(@RequestBody MemberDTO memberDTO, HttpServletRequest request) {
		// 로그인이 되면 세션화 적용하기
		String validateLogin = "n";
		if (memberService.login(memberDTO)) {
			HttpSession session = request.getSession();
			session.setAttribute("userId", memberDTO.getUserId());
			validateLogin = "y";
		}
		return validateLogin;
	}

	@GetMapping("/logout")
	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.invalidate();

		return "redirect:/usedallea/main";
	}

//	@GetMapping("/update")
//	public String update(HttpServletRequest request, Model model) {
//		HttpSession session = request.getSession();
//		String userId = (String) session.getAttribute("userId");
//		MemberDTO memberDetail = memberService.getMemberDetail(userId);
//
//		//null이면 객체로 반환하여 빈값으로 생성하기.
//		if (memberDetail == null) {
//			memberDetail = new MemberDTO();
//		}
//		model.addAttribute("memberDTO", memberDetail);
//		return "member/registerOrUpdate";
//	}

//	@PostMapping("/update")
//	public String update(@ModelAttribute MemberDTO memberDTO) {
//		memberService.updateMember(memberDTO);
//
//		return "redirect:/usedallea/main";
//	}

	@GetMapping("/delete")
	public String delete() {
		return "member/delete";
	}

	@PostMapping("/delete")
	public String delete(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userId");
		memberService.updateDelete(userId);  // 비활성화로 업데이트 하기

		session.invalidate();  // 세션 지우기
		return "redirect:/usedallea/main";
	}
}

	//탈퇴한 회원 스케줄링 하기



