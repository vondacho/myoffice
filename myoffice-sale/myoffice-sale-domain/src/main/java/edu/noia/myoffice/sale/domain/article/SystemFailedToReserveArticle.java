package edu.noia.myoffice.sale.domain.article;

import edu.noia.myoffice.sale.domain.cart.CartEvent;
import edu.noia.myoffice.sale.domain.cart.CartId;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
public class SystemFailedToReserveArticle implements CartEvent, ArticleEvent {
    CartId cartId;
    ArticleId articleId;
    Long quantity;
    Long availableQuantity;
}
