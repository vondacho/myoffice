package edu.noia.myoffice.sale.rest.endpoint;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.noia.myoffice.common.application.command.CommandPublisher;
import edu.noia.myoffice.common.domain.vo.Amount;
import edu.noia.myoffice.common.domain.vo.Quantity;
import edu.noia.myoffice.common.domain.vo.Rate;
import edu.noia.myoffice.common.domain.vo.Tariff;
import edu.noia.myoffice.common.mixin.QuantityMixin;
import edu.noia.myoffice.common.mixin.RateMixin;
import edu.noia.myoffice.common.serializer.CommonSerializers;
import edu.noia.myoffice.sale.application.command.cart.CreateCartCommand;
import edu.noia.myoffice.sale.common.mixin.CartItemMixin;
import edu.noia.myoffice.sale.common.serializer.SaleSerializers;
import edu.noia.myoffice.sale.domain.article.Article;
import edu.noia.myoffice.sale.domain.article.ArticleId;
import edu.noia.myoffice.sale.domain.cart.CartId;
import edu.noia.myoffice.sale.domain.cart.CartItem;
import edu.noia.myoffice.sale.domain.cart.CartItemId;
import edu.noia.myoffice.sale.domain.cart.CartType;
import edu.noia.myoffice.sale.domain.folder.FolderId;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = CartEndpoint.class)
public class CartEndpointIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CommandPublisher publisher;

    @Before
    public void setup() {
        objectMapper
                .addMixIn(CartItem.class, CartItemMixin.class)
                .addMixIn(Quantity.class, QuantityMixin.class)
                .addMixIn(Rate.class, RateMixin.class)
                //.addMixIn(Tariff.class, TariffMixin.class)
                .registerModules(CommonSerializers.getModule(), SaleSerializers.getModule());
    }

    @Test
    public void should_return_204_on_valid_cart_to_create() throws Exception {
        // given
        CreateCartCommand command = CreateCartCommand.of(FolderId.of(UUID.randomUUID()), CartType.ESTIMATE);
        // when
        mockMvc.perform(post("/api/sale/v1/carts")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(command)))
                // then
                .andExpect(status().isNoContent());
    }

    @Test
    public void should_return_204_on_valid_item_to_add() throws Exception {
        // given
        Article article = Article.of(ArticleId.of(UUID.randomUUID()), "test", Tariff.of(Amount.ZERO, Quantity.ONE));
        CartItem item = CartItem.of(CartItemId.random(), article, Quantity.ONE, LocalDateTime.now());
        CartId cartId = CartId.of(UUID.randomUUID());
        // when
        mockMvc.perform(put("/api/sale/v1/carts/{cartId}/items", cartId)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(item)))
                // then
                .andExpect(status().isNoContent());
    }
}
