package org.ssafy.d210.items.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.ssafy.d210.items.entity.Items;

import java.util.Optional;

@Repository
public interface ItemsRepository extends JpaRepository<Items, Long> {
    Optional<Items> findItemsById(Long itemsId);
}
