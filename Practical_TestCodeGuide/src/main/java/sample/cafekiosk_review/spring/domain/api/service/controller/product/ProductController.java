package sample.cafekiosk_review.spring.domain.api.service.controller.product;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import sample.cafekiosk_review.spring.domain.api.service.controller.product.dto.request.ProductCreateRequest;
import sample.cafekiosk_review.spring.domain.api.service.product.ProductService;
import sample.cafekiosk_review.spring.domain.api.service.product.response.ProductResponse;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping("/api/v1/product/new")
    public void createProduct(ProductCreateRequest request) {
        productService.createProduct(request);

    }

    @GetMapping("/api/v1/products/selling")
    public List<ProductResponse> getSellingProducts() {
        return productService.getSellingProducts();
    }


}
