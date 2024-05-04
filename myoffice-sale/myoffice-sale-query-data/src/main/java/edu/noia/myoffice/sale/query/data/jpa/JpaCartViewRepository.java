package edu.noia.myoffice.sale.query.data.jpa;

import edu.noia.myoffice.sale.domain.cart.CartId;
import edu.noia.myoffice.sale.domain.folder.FolderId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;
import java.util.Optional;

@RepositoryRestResource(path = "carts", collectionResourceRel = "carts", itemResourceRel = "cart")
public interface JpaCartViewRepository extends CrudRepository<JpaCartView, Long> {

    @RestResource(path = "byId", rel = "findById")
    Optional<JpaCartView> findById(CartId id);

    @RestResource(path = "byFolder", rel = "findByFolder")
    List<JpaCartView> findByFolderId(FolderId id);

    @Override
    @RestResource(exported = false)
    JpaCartView save(JpaCartView entity);

    @Override
    @RestResource(exported = false)
    void delete(JpaCartView entity);
}
