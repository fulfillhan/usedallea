package com.application.usedallea.product.controller;

import java.util.List;

import com.application.usedallea.img.dto.ProductImgDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

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
	public String create(@RequestParam("uploadImg") List<MultipartFile> uploadFile, ProductDTO productDTO, ProductImgDTO productImgDTO) throws Exception {

		productService.createProduct(uploadFile,productDTO,productImgDTO);

		return "product/productDetail";
		
	}
	// 상품 상세
	@GetMapping("/detail")
	public String detail(Model model,HttpServletRequest request,
						 @RequestParam("productId") long productId, @RequestParam("productImgId") long productImgId){
		//상세 상품 데이터 처음 가져올때만 조회수 반영
	  HttpSession session = request.getSession();
	    model.addAttribute("sellerId", session.getAttribute("userId"));   //찜 기능이 있어서 세션정보 필요
		model.addAttribute("productDTO",productService.getProductDetail(productId,true));
		model.addAttribute("imgUUID", productService.getImgUUID(productImgId));
		//<!--사진이 여러장이 아니면... imgDTO로 받아야 하는지?-->

		return "product/productDetail";
	}

}


