package com.pg3402.csgobank.vaultAccount;


import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.Optional;

@Service
public class VaultAccountService {

    private final VaultAccountRepository vaultAccountRepository;

    public VaultAccountService(VaultAccountRepository vaultAccountRepository) {
        this.vaultAccountRepository = vaultAccountRepository;
    }

    public VaultAccount save(VaultAccount account) {
        return vaultAccountRepository.save(account);
    }

    public Optional<VaultAccount> findById(long id) {
        return vaultAccountRepository.findById(id);
    }

}
