package edu.noia.myoffice.sale.application.command.article;

import edu.noia.myoffice.common.application.command.Command;
import edu.noia.myoffice.sale.domain.article.ArticleId;

public interface ArticleCommand extends Command {

    ArticleId getArticleId();
}
