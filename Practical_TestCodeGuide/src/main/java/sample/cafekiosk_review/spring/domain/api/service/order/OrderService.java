package sample.cafekiosk_review.spring.domain.api.service.order;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sample.cafekiosk_review.spring.domain.api.service.order.request.OrderCreateRequest;
import sample.cafekiosk_review.spring.domain.api.service.order.response.OrderResponse;
import sample.cafekiosk_review.spring.domain.order.Order;
import sample.cafekiosk_review.spring.domain.order.OrderRepository;
import sample.cafekiosk_review.spring.domain.product.Product;
import sample.cafekiosk_review.spring.domain.product.ProductRepository;
import sample.cafekiosk_review.spring.domain.product.ProductType;
import sample.cafekiosk_review.spring.domain.stock.Stock;
import sample.cafekiosk_review.spring.domain.stock.StockRepository;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final StockRepository stockRepository;


    /**
     * 재고 감소 -> 동시성 고민 필수..
     * Optimistic Lock Or Pessimistic Lock 사용
     * DB에 대한 Lock을 잡고 순차적으로 처리한다?
     *
     */
    public OrderResponse createOrder(final OrderCreateRequest request, LocalDateTime registeredDateTime) {
        List<String> productNumbers = request.getProductNumbers();
        // Product 조회
        List<Product> products = findProductsBy(productNumbers);

        // 재고 차감 로직 수행
        deductStockQuantities(products);

        // Order 생성

        Order order = Order.create(products, registeredDateTime);
        Order savedOrder = orderRepository.save(order);

        return OrderResponse.of(savedOrder);

    }

    private void deductStockQuantities(final List<Product> products) {
        // 재고 차감 체크가 필요한 상품들 Filter
        // -> 재고가 필요한 상품번호들만 조회
        List<String> stockProductNumbers = extractStockProductNumbers(products);
        // 재고 Entity 조회
        Map<String, Stock> stockMap = createStockMapBy(stockProductNumbers);

        // 상품별 counting
        Map<String, Long> productCountingMap = createCountingMapBy(stockProductNumbers);


        // 재고 차감 시도
        for (String stockProductNumber : new HashSet<>(stockProductNumbers)) {
            Stock stock = stockMap.get(stockProductNumber);
            int quantity = productCountingMap.get(stockProductNumber).intValue();
            if (stock.isQuantityLessThan(quantity)) {
                throw new IllegalArgumentException("재고가 부족한 상품이 있습니다.");
            }
            stock.deductQuantity(quantity);
        }
    }

    private static Map<String, Long> createCountingMapBy(final List<String> stockProductNumbers) {
        Map<String, Long> productCountingMap = stockProductNumbers.stream()
                .collect(Collectors.groupingBy(p -> p, Collectors.counting()));
        return productCountingMap;
    }

    private Map<String, Stock> createStockMapBy(final List<String> stockProductNumbers) {
        List<Stock> stocks = stockRepository.findAllByProductNumberIn(stockProductNumbers);

        Map<String, Stock> stockMap = stocks.stream()
                .collect(Collectors.toMap(Stock::getProductNumber, s -> s));
        return stockMap;
    }

    private List<Product> findProductsBy(final List<String> productNumbers) {
        List<Product> products = productRepository.findAllByProductNumberIn(productNumbers);
        Map<String, Product> productMap =
                products.stream().collect(Collectors.toMap(Product::getProductNumber, p -> p));

        List<Product> duplicateProducts = productNumbers.stream()
                .map(productMap::get)
                .collect(Collectors.toList());
        return duplicateProducts;
    }

    private static List<String> extractStockProductNumbers(final List<Product> products) {
        List<String> stockProductNumbers = products.stream()
                .filter(p -> ProductType.containsStockType(p.getType()))
                .map(Product::getProductNumber)
                .collect(Collectors.toList());
        return stockProductNumbers;
    }


}
