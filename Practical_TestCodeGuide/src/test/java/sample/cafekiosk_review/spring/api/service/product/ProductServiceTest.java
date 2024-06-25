package sample.cafekiosk_review.spring.api.service.product;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import sample.cafekiosk_review.spring.api.controller.product.dto.request.ProductCreateRequest;
import sample.cafekiosk_review.spring.api.service.order.request.ProductServiceCreateRequest;
import sample.cafekiosk_review.spring.api.service.product.ProductService;
import sample.cafekiosk_review.spring.api.service.product.response.ProductResponse;
import sample.cafekiosk_review.spring.domain.product.Product;
import sample.cafekiosk_review.spring.domain.product.ProductRepository;
import sample.cafekiosk_review.spring.domain.product.ProductSellingStatus;
import sample.cafekiosk_review.spring.domain.product.ProductType;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static sample.cafekiosk_review.spring.domain.product.ProductSellingStatus.*;
import static sample.cafekiosk_review.spring.domain.product.ProductType.HANDMADE;

@SpringBootTest
@ActiveProfiles("test")
class ProductServiceTest {
    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepository productRepository;

    @AfterEach
    void tearDown() {
        productRepository.deleteAllInBatch();
    }

    @DisplayName("신규 상품을 등록한다. 상품 번호는 가장 최근 상품의 상품번호에서 1 증가한 값이다.")
    @Test
    void createProduct() {
        //given
        Product product1 = createProduct("001", HANDMADE, SELLING);
        productRepository.save(product1);

        ProductServiceCreateRequest request = ProductServiceCreateRequest.builder()
                .type(HANDMADE)
                .sellingStatus(SELLING)
                .name("카푸치노")
                .price(5000)
                .build();


        //when
        ProductResponse productResponse = productService.createProduct(request);

        //then
        assertThat(productResponse)
                .extracting("productNumber", "type", "sellingStatus", "name", "price")
                .contains("002", HANDMADE, SELLING, "카푸치노");

        List<Product> products = productRepository.findAll();
        assertThat(products).hasSize(2)
                .extracting("productNumber", "type", "sellingStatus", "name", "price")
                .containsExactlyInAnyOrder(
                        tuple("001", HANDMADE, SELLING, "아메리카노", 4000),
                        tuple("002", HANDMADE, SELLING, "카푸치노", 5000));

    }


    @DisplayName("상품이 하나도 없는 경우 신규 상품을 등록하면 상품번호는 001이다.")
    @Test
    void createProductWhenProductIsEmpty() {

        //given

        ProductServiceCreateRequest request = ProductServiceCreateRequest.builder()
                .type(HANDMADE)
                .sellingStatus(SELLING)
                .name("카푸치노")
                .price(5000)
                .build();


        //when
        ProductResponse productResponse = productService.createProduct(request);

        //then
        List<Product> products = productRepository.findAll();
        assertThat(products).hasSize(1)
                .extracting("productNumber", "type", "sellingStatus", "name", "price")
                .contains(
                        tuple("001", HANDMADE, SELLING, "카푸치노", 5000));

    }

    private Product createProduct(String productNumber, ProductType type, ProductSellingStatus productSellingStatus) {
        return Product.builder()
                .productNumber(productNumber)
                .type(type)
                .sellingStatus(productSellingStatus)
                .name("아메리카노")
                .price(4000)
                .build();
    }

}
