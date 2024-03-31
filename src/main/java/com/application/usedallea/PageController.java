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
    public String registProduct() {
        return "product/productRegister";
    }

}
