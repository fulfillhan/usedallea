package com.application.usedallea.product.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.application.usedallea.img.dto.ProductImgDTO;
import com.application.usedallea.product.service.ProductStatus;
import com.application.usedallea.zzim.service.ZzimService;
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

//	@Value("${file.repo.path")
//	private String imgRepositoryPath;

	@Autowired
	private ProductService productService;

	@Autowired
	private ZzimService zzimService;

	private String getUserId(HttpServletRequest request) {
		HttpSession session = request.getSession();
		return (String) session.getAttribute("userId");
	}

	// 중고 상품 등록
	@GetMapping("/create")
	public String create(Model model, HttpServletRequest request) {

		String sellerId = getUserId(request);
		model.addAttribute("sellerId", sellerId);

		return "product/createProduct";
	}

	@PostMapping("/create")
	public String create(@RequestParam("uploadImg") List<MultipartFile> uploadFile, ProductDTO productDTO, ProductImgDTO productImgDTO) throws Exception {

		long productId = productService.createProduct(uploadFile, productDTO, productImgDTO);

		return "redirect:/product/detail?productId=" + productId;

	}


	// 판매자에게 보이는 상품 상세
	@GetMapping("/detail")
	public String detailBySeller(Model model, HttpServletRequest request, @RequestParam("productId") long productId) {

		// 세션에서 현재 로그인 한 아이디 가져오기
		String userId = getUserId(request);

		model.addAttribute("userId", userId);
		model.addAttribute("productDTO", productService.getProductDetail(productId, true));
		model.addAttribute("zzimCount", zzimService.getZzimCount(productId));
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
		return "redirect:/product/productManager?sellerId=" + productDTO.getSellerId();  //상품 관리 페이지로 변경해주기
	}

	@GetMapping("/productManager")
	public String productManager(Model model,
								 @RequestParam("sellerId") String sellerId,
								 @RequestParam(name = "searchTitle", defaultValue = "") String searchTitle,
								 @RequestParam(name = "onePageProductCnt", defaultValue = "10") int onePageProductCnt,
								 @RequestParam(name = "currentPageNumber", defaultValue = "1") int currentPageNumber) {


		Map<String, String> searchCntMap = new HashMap<>();
		searchCntMap.put("searchTitle", searchTitle);
		searchCntMap.put("sellerId", sellerId);

		int allProductCntBySeller = productService.getAllProductCntBySeller(searchCntMap);  // 특정 판매자의 판매목록의 총 개수

		int allPageCnt = allProductCntBySeller / onePageProductCnt;
		if (allProductCntBySeller % onePageProductCnt != 0) {
			allPageCnt++;
		}

		int startPage = (currentPageNumber - 1) / 10 * 10 + 1;
		if (startPage == 0) {
			startPage = 1;
		}

		int endPage = startPage + 9;
		if (endPage > allPageCnt) {
			endPage = allPageCnt;
		}
		if (endPage == 0) {
			endPage = 1;
		}

		int startProductIdx = (currentPageNumber - 1) * onePageProductCnt;

		Map<String, Object> searchMap = new HashMap<>();
		searchMap.put("searchTitle", searchTitle);
		searchMap.put("startProductIdx", startProductIdx);
		searchMap.put("onePageProductCnt", onePageProductCnt);
		searchMap.put("sellerId", sellerId);
		List<ProductDTO> productListBySeller = productService.getProductListBySeller(searchMap);

		for (ProductDTO products : productListBySeller) {
			long productId = products.getProductId();
			int zzimCount = zzimService.getZzimCount(productId);
			products.setZzimCount(zzimCount);
			List<String> productImgUUIDs = productService.getImgUUIDList(productId);
			if (!productImgUUIDs.isEmpty()) {
				String firstImgUUID = productImgUUIDs.get(0);
				products.setFirstImgUUID(firstImgUUID);
			}
		}

		model.addAttribute("productListBySeller", productListBySeller);
		model.addAttribute("allProductCntBySeller", allProductCntBySeller);
		model.addAttribute("allPageCnt", allPageCnt);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		//model.addAttribute("startProductIdx",startProductIdx);
		model.addAttribute("onePageProductCnt", onePageProductCnt);
		model.addAttribute("currentPageNumber", currentPageNumber);

		return "product/productManagerSample";
	}


	@PostMapping("/updateProductStatus")
	@ResponseBody
	public Map<String,Object> updateProductStatus(@RequestParam("productId")long productId, @RequestParam("productStatus") String productStatus ){
		Map<String,Object> response = new HashMap<>();
		ProductStatus status = ProductStatus.valueOf(productStatus); //status값을 enum으로 변경하기
		String script = "";

			switch (status){
				case 판매중 -> {
					status = productService.updateProductStatus(productId,status);
					script = "상품 상태가 '판매중'으로 변경되었습니다.";
				}
				case 판매완료 -> {
					status = productService.updateProductStatus(productId,status);
					script = "상품 상태가 '판매완료'로 변경되었습니다.";
				}
				case 삭제 -> {
					productService.updateValidateProduct(productId);
					script  = "해당 상품이 삭제되었습니다.";
					response.put("deleted",true);
				}
				default ->
					//에러 처리
						System.out.println("오류 발생");
			}
			response.put("status",String.valueOf(status));
		response.put("script",script);
		return response;
	}


}


