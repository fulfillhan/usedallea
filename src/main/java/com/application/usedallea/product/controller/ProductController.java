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
		model.addAttribute("sellerId", session.getAttribute("userId"));
//		if (로그인이 안되어있으면){
//			/**
//			 * 만약 로그인으로 가졌다.
//			 * 그럼 로그인이 된 후 다시 판매하기 메뉴로 이동시켜줘야 하는데, 어떻게 구현할 수 있을까?
//			 */
//			return "member/login";
//		}
		return "product/createProduct";
	}

	@PostMapping("/create")
	public String create(@RequestParam("uploadImg") List<MultipartFile> uploadFile, ProductDTO productDTO, ProductImgDTO productImgDTO) throws Exception {

		long productId = productService.createProduct(uploadFile, productDTO, productImgDTO);

		return "redirect:/product/detailBySeller?productId=" + productId;

	}


	// 판매자에게 보이는 상품 상세
	@GetMapping("/detailBySeller")
	public String detailBySeller(Model model, HttpServletRequest request, @RequestParam("productId") long productId) {
		//상세 상품 데이터 처음 가져올때만 조회수 반영
		HttpSession session = request.getSession();
		model.addAttribute("sellerId", session.getAttribute("userId"));   //찜 기능이 있어서 세션정보 필요
		model.addAttribute("productDTO", productService.getProductDetail(productId, false));
		model.addAttribute("imgUUIDList", productService.getImgUUIDList(productId));

		return "product/productDetailBySeller";
	}

	//상품 수정 productId를 보내준다.
	@GetMapping("/update")
	public String update(Model model, @RequestParam("productId") long productId) {
		model.addAttribute("productDTO", productService.getProductDetail(productId, false));
		return "product/updateProduct";
	}

	@PostMapping("/update")
	public String update(@ModelAttribute ProductDTO productDTO) {
		productService.updateProduct(productDTO);
		return "redirect:/product/detailBySeller";  //상품 관리 페이지로 변경해주기
	}

	@PostMapping("/delete")   // 상품 관리 페이지에서 삭제 버튼을 누르면 해당 컨트롤러 실행하여 삭제해주기!
	@ResponseBody
	public String delete(@RequestParam("productId") long productId){
		productService.deleteProduct(productId);

		String script= """
				<script>
				alert("게시글이 삭제되었습니다!");
				location.href='product/productManager'; 
				</script>
				""";
			// 나중에 상품 관리 페이지로 넘어가게한다.
		return script;
	}




}


