package sample.cafekiosk_review.spring.api.controller.product;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import sample.cafekiosk_review.spring.api.APiResponse;
import sample.cafekiosk_review.spring.api.service.product.ProductService;
import sample.cafekiosk_review.spring.api.service.product.response.ProductResponse;
import sample.cafekiosk_review.spring.api.controller.product.dto.request.ProductCreateRequest;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping("/api/v1/product/new")
    public APiResponse<ProductResponse> createProduct(@RequestBody @Valid ProductCreateRequest request) {

        return APiResponse.ok(productService.createProduct(request.toServiceRequest()));

    }

    @GetMapping("/api/v1/products/selling")
    public APiResponse<List<ProductResponse>> getSellingProducts() {
        return APiResponse.ok(productService.getSellingProducts());
    }


}
