package com.queerxdisasster.altshare.repository;

import com.queerxdisasster.altshare.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
}
