package pinetree.board.article.service;

import kuke.board.common.snowflake.Snowflake;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pinetree.board.article.entity.Article;
import pinetree.board.article.repository.ArticleRepository;
import pinetree.board.article.service.request.ArticleCreateRequest;
import pinetree.board.article.service.request.ArticleUpdateRequest;
import pinetree.board.article.service.response.ArticleResponse;

@Service
@RequiredArgsConstructor
public class ArticleService {
    private final Snowflake snowflake = new Snowflake();
    private final ArticleRepository articleRepository;

    @Transactional
    public ArticleResponse create(ArticleCreateRequest request) {

        Article savedArticle = articleRepository.save(
                Article.create(snowflake.nextId(),
                        request.getTitle(),
                        request.getContent(),
                        request.getWriterId(),
                        request.getBoardId())
        );

        return ArticleResponse.from(savedArticle);

    }

    @Transactional
    public ArticleResponse update(Long articleId, ArticleUpdateRequest updateRequest) {
        Article article = articleRepository.findById(articleId)
                .orElseThrow();
        article.update(updateRequest.getTitle(), updateRequest.getContent());
        return ArticleResponse.from(article);
    }

    public ArticleResponse read(Long articleId) {
        return ArticleResponse.from(articleRepository.findById(articleId).orElseThrow());
    }

    @Transactional
    public void delete(Long articleId) {
        articleRepository.deleteById(articleId);
    }
}
