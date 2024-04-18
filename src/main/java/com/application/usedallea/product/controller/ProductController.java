package com.application.usedallea.product.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.application.usedallea.img.dto.ProductImgDTO;
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
		return  (String) session.getAttribute("userId");
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
		model.addAttribute("productDTO", productService.getProductDetail(productId,true));
		model.addAttribute("zzimCount", zzimService.getZzimCount(productId));
		model.addAttribute("imgUUIDList", productService.getImgUUIDList(productId));


		return "product/productDetailBySeller";
	}

	//상품 수정 productId를 보내준다.
	@GetMapping("/update")
	public String update(Model model,@RequestParam("productId") long productId) {
		model.addAttribute("productDTO", productService.getProductDetail(productId,false));
		return "product/updateProduct";
	}

	@PostMapping("/update")
	public String update(@ModelAttribute ProductDTO productDTO) {
		productService.updateProduct(productDTO);
		return "redirect:/product/productManager?sellerId="+productDTO.getSellerId();  //상품 관리 페이지로 변경해주기
	}

	@PostMapping("/delete")   // 상품 관리 페이지에서 삭제 버튼을 누르면 해당 컨트롤러 실행하여 삭제해주기!
	@ResponseBody
	public String delete(@RequestParam("productId") long productId) {
		productService.deleteProduct(productId);

		String script = """
				<script>
				alert("게시글이 삭제되었습니다!");
				location.href='product/productManager'; 
				</script>
				""";
		// 나중에 상품 관리 페이지로 넘어가게한다.
		return script;
	}

	@GetMapping("/productManager")
	public String productManager(Model model,
								 @RequestParam("sellerId") String sellerId,
								 @RequestParam(name = "searchTitle", defaultValue = "") String searchTitle,
								 @RequestParam(name = "onePageProductCnt", defaultValue = "10") int onePageProductCnt,
								 @RequestParam(name = "currentPageNumber", defaultValue = "1") int currentPageNumber){


		Map<String, String> searchCntMap = new HashMap<>();
		searchCntMap.put("searchTitle",searchTitle);
		searchCntMap.put("sellerId",sellerId);

		int allProductCntBySeller = productService.getAllProductCntBySeller(searchCntMap);  // 특정 판매자의 판매목록의 총 개수

		int allPageCnt = allProductCntBySeller / onePageProductCnt;
		if(allProductCntBySeller % onePageProductCnt != 0){
			allPageCnt++;
		}

		int startPage = (currentPageNumber-1)/ 10 * 10 +1;
		if(startPage == 0){
			startPage = 1;
		}

		int endPage = startPage + 9;
		if (endPage > allPageCnt) {
			endPage = allPageCnt;
		}
		if(endPage == 0){
			endPage = 1;
		}

		int startProductIdx = (currentPageNumber-1) * onePageProductCnt;

		Map<String, Object> searchMap =  new HashMap<>();
		searchMap.put("searchTitle",searchTitle);
		searchMap.put("startProductIdx", startProductIdx);
		searchMap.put("onePageProductCnt", onePageProductCnt);
		searchMap.put("sellerId", sellerId);
		List<ProductDTO> productListBySeller = productService.getProductListBySeller(searchMap);
		for(ProductDTO products : productListBySeller){
			List<String> productImgUUIDs = productService.getImgUUIDList(products.getProductId());
		if(!productImgUUIDs.isEmpty()){
			String firstImgUUID = productImgUUIDs.get(0);
			products.setFirstImgUUID(firstImgUUID);
		}
	}
		model.addAttribute("productListBySeller",productListBySeller);
		model.addAttribute("allProductCntBySeller",allProductCntBySeller);
		model.addAttribute("allPageCnt",allPageCnt);
		model.addAttribute("startPage",startPage);
		model.addAttribute("endPage",endPage);
		model.addAttribute("onePageProductCnt",onePageProductCnt);
		model.addAttribute("currentPageNumber",currentPageNumber);
		return "product/productManagerSample";
	}




}


