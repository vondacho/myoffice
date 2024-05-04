package edu.noia.myoffice.sale.application.command.cart;

import edu.noia.myoffice.common.application.command.Command;
import edu.noia.myoffice.sale.domain.cart.CartType;
import edu.noia.myoffice.sale.domain.folder.FolderId;
import lombok.*;
import lombok.experimental.FieldDefaults;

@ToString
@Getter
@RequiredArgsConstructor(staticName = "of")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateCartCommand implements Command {

    @NonNull
    FolderId folderId;
    @NonNull
    CartType type;
    String title;
    String notes;
}
