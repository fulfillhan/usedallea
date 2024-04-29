package com.application.usedallea.admin.controller;

import com.application.usedallea.admin.dto.AdminDTO;
import com.application.usedallea.admin.service.AdminService;
import com.application.usedallea.member.dto.MemberDTO;
import com.application.usedallea.member.service.MemberService;
import com.application.usedallea.product.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public String login() {
        return "admin/login";
    }

    @PostMapping("/login")
    @ResponseBody
    public String login(HttpServletRequest request, @RequestBody AdminDTO adminDTO) {
        String validateLogin = "n";
        if (adminService.login(adminDTO)) {
            HttpSession session = request.getSession();
            session.setAttribute("adminId", adminDTO.getAdminId());
            validateLogin = "y";
        }
        return validateLogin;
    }

    @GetMapping("/userList")
    public String page (Model model,
                        @RequestParam(name = "onePageViewCnt", defaultValue = "20") int onePageViewCnt,
                        @RequestParam(name="currentPageNumber", defaultValue = "1") int currentPageNumber) {


        //회원 목록을 가지고 와야함.
        List<MemberDTO> memberList = memberService.memberList();

        //저체 회원의 상품의 갯수
        for (MemberDTO member : memberList){
            String sellerId = member.getUserId();
            int productCnt = productService.getProducCntByUser(sellerId);
            member.setProductCnt(productCnt);

            if(member.getActiveYn().equals("n")) member.setActiveYn("비할성화");
            if(member.getActiveYn().equals("y")) member.setActiveYn("활성화");
        }

        //전체 사용자 인원수
        int allUserCnt = memberService.getAllUserCnt();

        int allPageCnt = allUserCnt / onePageViewCnt;
        if(allUserCnt % onePageViewCnt  != 0 ){
            allPageCnt ++;
        }

        int startPage = (currentPageNumber-1)/10 *10 +1;
        if(startPage == 0){
            startPage =1;
        }

        int endPage = startPage + 9;
        if(endPage > allPageCnt){
            endPage = allPageCnt;
        }
        if(endPage == 0){
            endPage = 1;
        }

        int startViewIdx = (currentPageNumber-1)*onePageViewCnt;

      model.addAttribute("memberList", memberList);
      model.addAttribute("allUserCnt", allUserCnt);
      model.addAttribute("allPageCnt", allPageCnt);
      model.addAttribute("startPage", startPage);
      model.addAttribute("endPage", endPage);
      model.addAttribute("startViewIdx", startViewIdx);
      model.addAttribute("currentPageNumber",currentPageNumber);
      model.addAttribute("onePageViewCnt",onePageViewCnt);



        return "admin/userListPage";
    }

    @PostMapping("/removeUser")
    @ResponseBody
    public String removeUser(@RequestParam("userId") String userId) {

        try {
            memberService.removeUser(userId);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "errors";
        }
    }

//    @GetMapping("/productList")
//    public String productList(){
//        return "admin/productListPage";
//    }

}


