package com.pg3402.csgobank.vaultAccount;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VaultAccountRepository extends CrudRepository<VaultAccount,Long> {
    Optional<VaultAccount> findByEmail(String email);

}
