package edu.noia.myoffice.sale.application.command.article;

import edu.noia.myoffice.common.application.command.Command;
import edu.noia.myoffice.sale.domain.article.ArticleId;

import java.util.Map;

public interface ArticleListCommand<T> extends Command {

    Map<ArticleId, T> getArticles();
}
