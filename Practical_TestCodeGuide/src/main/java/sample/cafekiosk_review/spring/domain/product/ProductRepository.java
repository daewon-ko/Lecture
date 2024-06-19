package sample.cafekiosk_review.spring.domain.product;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    /**
     * select *
     * from product
     * where selling_type in('SELLING', 'HOLD');
     *
     * @return
     */
    List<Product> findAllBySellingStatusIn(List<ProductSellingStatus> sellingStatus);

    List<Product> findAllByProductNumberIn(List<String> productNumbers);


}
