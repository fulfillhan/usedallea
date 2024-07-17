package com.application.usedallea.product.controller;

import com.application.usedallea.img.Service.ImgService;
import com.application.usedallea.img.dto.ImgRegisterDto;
import com.application.usedallea.old.product.service.ProductStatus;
import com.application.usedallea.product.dto.ProductModifyDto;
import com.application.usedallea.product.dto.ProductRegisterDto;
import com.application.usedallea.product.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final ImgService imgService;

    @GetMapping("/register-page")
    public String toRegisterPage() {
        return "product/createOrUpdate";
    }

    //상품등록하기(이미지등록 어려움)
    @PostMapping
    public String create(HttpSession session,
                         @RequestParam List<MultipartFile> uploadImg,
                         @ModelAttribute ProductRegisterDto productDto,
                         @ModelAttribute ImgRegisterDto imgDto) throws Exception {
        String sellerId = (String) session.getAttribute("userId");
        if (sellerId == null) {
            return "redirect:/login";
        }
       // productDto.setSellerId(sellerId);
        ProductRegisterDto.builder().sellerId(sellerId).build();
        long productId = productService.saveProduct(uploadImg, productDto, imgDto);
        return "redirect:/products/" + productId;  // 상세 페이지로 이동
    }

    //상세 페이지 -> 확인 필요
    @GetMapping("/{productId}")
    public String Detail(@PathVariable long productId, HttpSession session, Model model) {
        // 세션에서 로그인한 정보 가져오기
        String userId = (String) session.getAttribute("userId");

        ProductRegisterDto productDto = productService.findByProductId(productId, true);
        List<String> imgUUIDList = productService.findImgUUIDs(productId);

        // 뷰로 전달
        model.addAttribute("userId", userId);
        model.addAttribute("productDTO", productDto);
        model.addAttribute("imgUUIDList", imgUUIDList);

        return "product/productDetailBySeller";
    }

    // 상품의 상태 변경하기 2.

    //특정상품 수정하기(확인 필요)
    @PutMapping("/{productId}")
    public String update(@PathVariable long productId, @RequestBody ProductModifyDto productDto) {
        productDto.setProductId(productId);
        productService.updateProduct(productDto);
        // sellerId도 필요...
        return "product/productDetailBySeller";  // 수정한 후 상세페이지로 이동...
    }

    // 상품 전체 목록 가져오기 - 기존 메인에 있던것(페이징)
    @GetMapping
    public ResponseEntity<Void> products() {
        return ResponseEntity.ok().build();
    }

    // 특정 판매자가 등록한 상품 전체 목록  -> 페이징이 들어감...
    @GetMapping
    public ResponseEntity<Void> findAll(HttpServletRequest request, @RequestBody ProductRegisterDto productDto) {
        HttpSession session = request.getSession();

        return ResponseEntity.ok().build();
    }

    @PutMapping("/{productId}/{productStatus}")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> updateStatus(@PathVariable long productId, @PathVariable String productStatus) {
        Map<String, Object> response = new HashMap<>();
        ProductStatus status = ProductStatus.valueOf(productStatus); //status값을 enum으로 변경하기
        String script = "";

        status = productService.updateProductStatus(productId, status);

        // 하단의 로직은 비즈니스 로직쪽으로 이동?
        switch (status) {
            case 판매중 -> {
                // status = productService.updateProductStatus(productId,status);
                script = "상품 상태가 '판매중'으로 변경되었습니다.";
            }
            case 판매완료 -> {
                // status = productService.updateProductStatus(productId,status);
                script = "상품 상태가 '판매완료'로 변경되었습니다.";
            }
            case 삭제 -> {
                // productService.updateValidateProduct(productId);
                script = "해당 상품이 삭제되었습니다.";
                response.put("deleted", true);
            }
            default ->
                //에러 처리
                    System.out.println("오류 발생");
        }
        response.put("status", String.valueOf(status));
        response.put("script", script);
        return ResponseEntity.ok().build();
    }

}
