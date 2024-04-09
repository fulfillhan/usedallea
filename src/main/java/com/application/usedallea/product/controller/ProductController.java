package com.application.usedallea.product.controller;

import java.util.List;

import com.application.usedallea.img.dto.ProductImgDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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

		  long productId= productService.createProduct(uploadFile,productDTO,productImgDTO);

		return "redirect:/product/detailBySeller?productId="+productId;
		
	}


	// 판매자에게 보이는 상품 상세
	@GetMapping("/detailBySeller")
	public String detailBySeller(Model model,HttpServletRequest request, @RequestParam("productId") long productId){
		//상세 상품 데이터 처음 가져올때만 조회수 반영
	  HttpSession session = request.getSession();
	    model.addAttribute("sellerId", session.getAttribute("userId"));   //찜 기능이 있어서 세션정보 필요
		model.addAttribute("productDTO",productService.getProductDetail(productId,false));
		model.addAttribute("imgUUIDList", productService.getImgUUID(productId));

		return "product/productDetailBySeller";
	}

	//상품 수정
//	@GetMapping("/update")
//	public String update(Model model, @RequestParam("productId") long productId){
//		model.addAttribute("productDTO", productService.getProductDetail(productId,false));
//		return "product/updateProduct";
//		}

}


