package edu.noia.myoffice.sale.common.serializer;

import edu.noia.myoffice.common.domain.vo.Amount;
import edu.noia.myoffice.common.domain.vo.Quantity;
import edu.noia.myoffice.common.domain.vo.Tariff;
import edu.noia.myoffice.sale.domain.article.Article;
import edu.noia.myoffice.sale.domain.article.ArticleId;
import edu.noia.myoffice.sale.domain.cart.CartItem;
import edu.noia.myoffice.sale.domain.cart.CartItemId;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.JsonContent;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@JsonTest
public class SaleSerializersTest {

    @Autowired
    private JacksonTester<CartItem> json;

    @Test
    public void should_serialize_cart_item() throws Exception {
        // given
        Article article = Article.of(ArticleId.of(UUID.randomUUID()), "test", Tariff.of(Amount.ZERO, Quantity.ONE));
        CartItem item = CartItem.of(CartItemId.random(), article, Quantity.ONE, LocalDateTime.now());
        // when
        JsonContent<CartItem> content = json.write(item);
        // then
        assertThat(content).hasJsonPathStringValue("@.tariff");
        assertThat(content).extractingJsonPathStringValue("@.tariff").isEqualTo("Honda");
    }
}
