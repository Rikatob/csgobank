package com.pg3402.csgobank.item;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends CrudRepository<Item,Long> {
    Iterable<Item> findAllByVaultId(Long vaultId);
}
