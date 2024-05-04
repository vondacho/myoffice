package edu.noia.myoffice.sale.domain.article;

import edu.noia.myoffice.sale.domain.cart.CartEvent;
import edu.noia.myoffice.sale.domain.cart.ItemEvent;

public interface SystemCancelledArticleReservation extends CartEvent, ItemEvent, ArticleEvent {
}
