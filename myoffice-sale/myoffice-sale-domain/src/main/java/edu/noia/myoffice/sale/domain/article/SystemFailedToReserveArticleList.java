package edu.noia.myoffice.sale.domain.article;

import edu.noia.myoffice.sale.domain.cart.CartEvent;
import edu.noia.myoffice.sale.domain.article.ArticleId;

import java.util.Map;

public interface SystemFailedToReserveArticleList extends CartEvent {

    Map<ArticleId, Long> getArticles();
}
