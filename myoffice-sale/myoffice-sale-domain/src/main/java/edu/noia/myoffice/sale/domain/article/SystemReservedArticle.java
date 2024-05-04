package edu.noia.myoffice.sale.domain.article;

import edu.noia.myoffice.common.domain.vo.Quantity;
import edu.noia.myoffice.sale.domain.cart.CartEvent;
import edu.noia.myoffice.sale.domain.cart.CartId;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
public class SystemReservedArticle implements CartEvent, ArticleEvent {
    CartId cartId;
    ArticleId articleId;
    Quantity quantity;
}
