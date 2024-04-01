package com.application.usedallea.product.controller;

import java.io.File;
import java.util.List;

import com.application.usedallea.member.dto.MemberDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.catalina.filters.ExpiresFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.application.usedallea.img.dto.ImgDTO;
import com.application.usedallea.product.dto.ProductDTO;
import com.application.usedallea.product.service.ProductService;

@Controller
@RequestMapping("/product")
public class ProductController {
	
	@Value("${file.repo.path")
	private String imgRepositoryPath;
	
	@Autowired
	private ProductService productService;
	
	
//	@GetMapping("/allProductList")
//	public String getAllProductList(Model model) {
//	List<ProductDTO> productList = productService.getAllProudctList();
//	
//	// 각 상품에 대한 이미지 정보 가져오기
//	for (ProductDTO product : productList) {
//		List<ImgDTO> imgList = productService.getProductImg();
//	}
//		
//		return "";	
//	}
	
	// 중고 상품 등록
	@GetMapping("/create")
	public String create(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		model.addAttribute("sellerId",session.getAttribute("userId"));
		return "product/createProduct";
	}
	
	@PostMapping("/create")
	public String create(@RequestParam("uploadImg") MultipartFile uploadFile, ProductDTO productDTO, ImgDTO imgDTO) throws Exception {
		productService.createProduct(uploadFile,productDTO,imgDTO);
		
		return "";
		
	}
	
	// 상품 삭제

}


