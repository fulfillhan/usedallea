package com.application.usedallea.main.controller;


import com.application.usedallea.img.service.ProductImgService;
import com.application.usedallea.member.service.MemberService;
import com.application.usedallea.product.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/usedallea")
public class MainController {

	@Autowired
	private MemberService memberService;

	@Autowired
	private ProductService productService;

	@Autowired
	private ProductImgService productImgService;

	@GetMapping("/main")
	public String main(HttpServletRequest request, Model model) {

	  //model.getAttribute("imgUUID", productService.getFirstImgUUID());
		return "common/main";
	}


}
