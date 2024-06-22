package sample.cafekiosk_review.spring.api.service.product;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sample.cafekiosk_review.spring.api.service.product.response.ProductResponse;
import sample.cafekiosk_review.spring.api.controller.product.dto.request.ProductCreateRequest;
import sample.cafekiosk_review.spring.domain.product.Product;
import sample.cafekiosk_review.spring.domain.product.ProductRepository;
import sample.cafekiosk_review.spring.domain.product.ProductSellingStatus;

import java.util.List;
import java.util.stream.Collectors;

/**
 * readOnly= true : 읽기전용
 * CRUD에서 CUD 동작 X / only Read만 가능
 * JPA: CUD 동작을 하지 않기때문에, 스냅샷 저장 또는 변경감지가 작동하지 않는다.
 * -> 성능 상 이점 생김.
 *
 * CQRS -> Command / Read 분리
 *
 */
@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    // 동시성 이슈 발생 가능
    public ProductResponse createProduct(final ProductCreateRequest request) {
        //productNumber 부여
        // 001 002 003
        // DB에서 마지막 저장된 Product의 상품번호를 읽어와서 +1


        //NextProductNUmber

        String nextProductNumber = createNextProductNumber();
        Product product = request.toEntity(nextProductNumber);
        Product savedProduct = productRepository.save(product);


        return ProductResponse.of(savedProduct);
    }

    public List<ProductResponse> getSellingProducts() {
        List<Product> products = productRepository.findAllBySellingStatusIn(ProductSellingStatus.forDisplay());

        return products.stream()
                .map(product -> ProductResponse.of(product))
                .collect(Collectors.toList());

    }
    private String createNextProductNumber() {
        String latestProductNumber = productRepository.findLatestProduct();
        if (latestProductNumber == null) {
            return "001";
        }
        int latestProductNumberInt = Integer.valueOf(latestProductNumber);
        int nextProductNumberInt = latestProductNumberInt + 1;
        return String.format("%03d", nextProductNumberInt);
    }
}
