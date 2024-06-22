package sample.cafekiosk_review.spring.domain.api.service.controller.product;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import sample.cafekiosk_review.spring.api.controller.product.ProductController;
import sample.cafekiosk_review.spring.api.controller.product.dto.request.ProductCreateRequest;
import sample.cafekiosk_review.spring.api.service.product.ProductService;
import sample.cafekiosk_review.spring.domain.product.ProductSellingStatus;
import sample.cafekiosk_review.spring.domain.product.ProductType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(controllers = ProductController.class)
class ProductControllerTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;


    @DisplayName("신규 상품을 등록한다.")
    @Test
    void test() throws Exception {
        //given
        ProductCreateRequest request = ProductCreateRequest.builder()
                .type(ProductType.HANDMADE)
                .sellingStatus(ProductSellingStatus.SELLING)
                .name("아메리카노")
                .price(4000)
                .build();
        //when // then
        mockMvc.perform
                        (post("/api/v1/product/new")
                                .content(objectMapper.writeValueAsString(request))
                                .contentType(MediaType.APPLICATION_JSON)
                        ).andDo(print())
                .andExpect(status().isOk());

    }


}
