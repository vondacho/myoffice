package edu.noia.myoffice.sale.application.command.article;

import edu.noia.myoffice.common.domain.vo.Quantity;
import edu.noia.myoffice.sale.application.command.cart.CartCommand;
import edu.noia.myoffice.sale.domain.article.ArticleId;
import edu.noia.myoffice.sale.domain.cart.CartId;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
@RequiredArgsConstructor(staticName = "of")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ReserveArticleCommand implements CartCommand, ArticleCommand {
    @NonNull
    CartId cartId;
    @NonNull
    ArticleId articleId;
    @NonNull
    Quantity quantity;
}
