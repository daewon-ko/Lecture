package sample.cafekiosk_review.spring.domain.product;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import sample.cafekiosk_review.spring.IntegrationTestSupport;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static sample.cafekiosk_review.spring.domain.product.ProductSellingStatus.*;
import static sample.cafekiosk_review.spring.domain.product.ProductType.*;

//@SpringBootTest

@Transactional
class ProductRepositoryTest extends IntegrationTestSupport {

    @Autowired
    private ProductRepository productRepository;

    @DisplayName("원하는 판매 상태를 가진 상품들을 조회한다.")
    @Test

    void test(){
    //given
        Product product1 = createProduct("001", HANDMADE, SELLING);
        Product product2 = createProduct("002", HANDMADE, HOLD);
        Product product3 = createProduct("003", HANDMADE, STOP_SELLING);


        productRepository.saveAll(List.of(product1, product2, product3));

        //when

        List<Product> products = productRepository.findAllBySellingStatusIn(List.of(SELLING, HOLD));

        //then
        // List에 대한 테스트
        // 1. 사이즈 체크
        assertThat(products).hasSize(2)
                .extracting("productNumber", "name", "sellingStatus")   // 추출
                .containsExactlyInAnyOrder(
                        tuple("001", "아메리카노", SELLING),
                        tuple("002", "아메리카노", HOLD)
                );    // 순서 상관 없이 검증



    }


    @DisplayName("상품번호 리스트로 상품들을 조회한다.")
    @Test

    void findAllByProductNumbersIn(){
        //given
        Product product1 = createProduct("001", HANDMADE, SELLING);
        Product product2 = createProduct("002", HANDMADE, HOLD);
        Product product3 = createProduct("003", HANDMADE, STOP_SELLING);


        productRepository.saveAll(List.of(product1, product2, product3));

        //when

        List<Product> products = productRepository.findAllByProductNumberIn(List.of("001", "002"));

        //then
        // List에 대한 테스트
        // 1. 사이즈 체크
        assertThat(products).hasSize(2)
                .extracting("productNumber", "name", "sellingStatus")   // 추출
                .containsExactlyInAnyOrder(
                        tuple("001", "아메리카노", SELLING),
                        tuple("002", "아메리카노", HOLD)
                );    // 순서 상관 없이 검증



    }

    @DisplayName("가장 마지막으로 저장한 상품 번호를 불러온다. ")
    @Test
    void findLatestProductNumber(){
        //given

        String targetProductNumber = "003";

        Product product1 = createProduct("001", HANDMADE, SELLING);
        Product product2 = createProduct("002", HANDMADE, HOLD);
        Product product3 = createProduct(targetProductNumber, HANDMADE, STOP_SELLING);

        productRepository.saveAll(List.of(product1, product2, product3));

        //when

        String latestProductNumber = productRepository.findLatestProduct();

        //then
        // List에 대한 테스트
        // 1. 사이즈 체크
        assertThat(latestProductNumber).isEqualTo(targetProductNumber);

    }


    @DisplayName("가장 마지막으로 저장한 상품의 상품번호를 읽어올때, 상품이 하나도 없는 경우에는 Null을 반환한다.")
    @Test
    void findLatestProductNumberWhenProductIsEmpty(){
        //when

        String latestProductNumber = productRepository.findLatestProduct();

        //then
        // List에 대한 테스트
        // 1. 사이즈 체크
        assertThat(latestProductNumber).isNull();

    }

    private  Product createProduct(String productNumber, ProductType type, ProductSellingStatus productSellingStatus) {
        return Product.builder()
                .productNumber(productNumber)
                .type(type)
                .sellingStatus(productSellingStatus)
                .name("아메리카노")
                .price(4000)
                .build();
    }
}
