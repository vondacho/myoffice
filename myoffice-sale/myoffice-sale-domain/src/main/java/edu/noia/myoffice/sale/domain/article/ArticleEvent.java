package edu.noia.myoffice.sale.domain.article;

import edu.noia.myoffice.common.domain.event.DomainEvent;
import edu.noia.myoffice.sale.domain.article.ArticleId;

public interface ArticleEvent extends DomainEvent {

    ArticleId getArticleId();
}
