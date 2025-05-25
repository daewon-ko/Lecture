package pinetree.board.article.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestClient;
import pinetree.board.article.service.response.ArticleResponse;

public class ArticleApiTest {
    RestClient restClient = RestClient.create("http://localhost:9000");


    @Test
    void createTest() {
        ArticleResponse response = create(new ArticleCreateRequest(
                "hi", "my content", 1L, 1L
        ));
        System.out.println("response = " + response);


    }

    @Test
    void readTest() {
        ArticleResponse response = read(184918881483468800L);
        System.out.println("response = " + response);

    }

    ArticleResponse read(Long articleId) {
        return restClient.get()
                .uri("/v1/articles/{articleId}", articleId)
                .retrieve()
                .body(ArticleResponse.class);
    }

    ArticleResponse create(ArticleCreateRequest request) {
        return restClient.post()
                .uri("/v1/articles")
                .body(request)
                .retrieve()
                .body(ArticleResponse.class);
    }

    @Test
    void updateTest() {
        update(184918881483468800L);
        ArticleResponse response = read(184918881483468800L);
        System.out.println("response = " + response);

    }

    @Test
    void deleteTest() {
        restClient.delete()
                .uri("/v1/articles/{articleId}", 184918881483468800L)
                .retrieve();
    }

    void update(Long articleId) {
        restClient.put()
                .uri("/v1/articles/{articleId}", articleId)
                .body(new ArticleUpdateRequest("hi 2", "my content 22"))
                .retrieve();
    }

    @Getter
    @AllArgsConstructor
    static class ArticleCreateRequest {
        private String title;
        private String content;
        private Long writerId;
        private Long boardId;
    }

    @Getter
    @ToString
    @AllArgsConstructor
    static class ArticleUpdateRequest {
        private String title;
        private String content;

    }


}
