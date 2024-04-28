package com.application.usedallea.admin.controller;

import com.application.usedallea.admin.dto.AdminDTO;
import com.application.usedallea.admin.service.AdminService;
import com.application.usedallea.member.service.MemberService;
import com.application.usedallea.product.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private MemberService memberService;

    @Autowired
    private ProductService productService;


    @GetMapping("/login")
    public String login (){
        return "admin/login";
    }

    @PostMapping("/login")
    @ResponseBody
    public String login(HttpServletRequest request, @RequestBody AdminDTO adminDTO){
        String validateLogin = "n";
        if (adminService.login(adminDTO)) {
            HttpSession session = request.getSession();
            session.setAttribute("adminId", adminDTO.getAdminId());
            validateLogin = "y";
        }
        return validateLogin;
    }

//    @GetMapping("/userList")
//    public String page (HttpServletRequest request, Model model){
//
//        HttpSession session = request.getSession();
//       String adminId = (String) session.getAttribute("adminId");
////
////       //회원 목록을 가지고 와야함.
////        //페이징 해야함.(상품명,회원아이디로 조회가능)
//
//
//        return "admin/userListPage";

        @GetMapping("/userList")
        public String userList (){
            return "admin/userListPage";
        }
    }


