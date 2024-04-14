package com.application.usedallea;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/page")
public class PageController {

    @RequestMapping("/home")
    public String home() {
        return "common/main";
    }

    @RequestMapping("/product/regigster")
    public String registerProduct() {
        return "product/productRegister";
    }

    @RequestMapping("/product/detail")
    public String registerDetail() {
        return "design/productDetail";
    }

    @RequestMapping("/my-store")
    public String myStore() {
        return "design/myStoreManage";
    }

}
