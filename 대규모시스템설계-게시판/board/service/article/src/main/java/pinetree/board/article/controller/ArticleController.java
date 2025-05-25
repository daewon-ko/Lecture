package pinetree.board.article.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pinetree.board.article.service.ArticleService;
import pinetree.board.article.service.request.ArticleCreateRequest;
import pinetree.board.article.service.request.ArticleUpdateRequest;
import pinetree.board.article.service.response.ArticleResponse;

@RestController
@RequiredArgsConstructor
public class ArticleController {
    private final ArticleService articleService;

    @GetMapping("/v1/articles/{articleId}")
    public ArticleResponse read(@PathVariable("articleId") Long articleId) {
        return articleService.read(articleId);
    }

    @PostMapping("/v1/articles")
    public ArticleResponse create(@RequestBody ArticleCreateRequest request) {
        return articleService.create(request);
    }

    @PutMapping("/v1/articles/{articleId}")
    public ArticleResponse update(@PathVariable("articleId") Long articleId,
                                  @RequestBody ArticleUpdateRequest request) {
        return articleService.update(articleId, request);
    }

    @DeleteMapping("/v1/articles/{articleId}")
    public void delete(@PathVariable("articleId") Long articleId) {
        articleService.delete(articleId);
    }
}
