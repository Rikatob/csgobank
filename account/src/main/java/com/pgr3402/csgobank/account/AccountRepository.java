package com.pgr3402.csgobank.account;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends CrudRepository<Account,Long> {
    Optional<Account> findByEmail(String email);

}
