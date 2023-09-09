package com.pg3402.csgobank.vault;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VaultRepository extends CrudRepository<Vault, Long> {
}
