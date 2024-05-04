package edu.noia.myoffice.sale.rest.endpoint;

import edu.noia.myoffice.common.application.command.CommandPublisher;
import edu.noia.myoffice.common.domain.vo.Amount;
import edu.noia.myoffice.common.domain.vo.Quantity;
import edu.noia.myoffice.common.domain.vo.Tariff;
import edu.noia.myoffice.sale.application.command.cart.CreateCartCommand;
import edu.noia.myoffice.sale.application.command.item.AddItemToCartCommand;
import edu.noia.myoffice.sale.domain.article.Article;
import edu.noia.myoffice.sale.domain.article.ArticleId;
import edu.noia.myoffice.sale.domain.cart.CartId;
import edu.noia.myoffice.sale.domain.cart.CartItem;
import edu.noia.myoffice.sale.domain.cart.CartItemId;
import edu.noia.myoffice.sale.domain.cart.CartType;
import edu.noia.myoffice.sale.domain.folder.FolderId;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class CartEndpointTest {

    @Mock
    private CommandPublisher publisher;

    @Test
    public void should_send_create_command_and_return_204() throws Exception {
        // given
        CreateCartCommand command = CreateCartCommand.of(FolderId.of(UUID.randomUUID()), CartType.ESTIMATE);
        // when
        new CartEndpoint(publisher).create(command);
        // then
        verify(publisher, times(1)).publish(command);
    }

    @Test
    public void should_send_add_item_command_and_return_204() throws Exception {
        // given
        Article article = Article.of(ArticleId.of(UUID.randomUUID()), "test", Tariff.of(Amount.ZERO, Quantity.ZERO));
        CartItem item = CartItem.of(CartItemId.random(), article, Quantity.ZERO, LocalDateTime.now());
        CartId cartId = CartId.of(UUID.randomUUID());
        // when
        new CartEndpoint(publisher).addItem(cartId, item);
        // then
        ArgumentCaptor<AddItemToCartCommand> commandCaptor = ArgumentCaptor.forClass(AddItemToCartCommand.class);
        verify(publisher, times(1)).publish(commandCaptor.capture());
        assertThat(commandCaptor.getValue().getCartId()).isEqualTo(cartId);
    }
}
