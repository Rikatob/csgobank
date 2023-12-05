package com.pgr3402.csgobank.account;


import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.Optional;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account save(Account account) {
        return accountRepository.save(account);
    }

    public boolean exists(Account account) {
        return accountRepository.findByEmail(account.getEmail()).isPresent();
    }

    public Optional<Account> findById(long id) {
        return accountRepository.findById(id);
    }


    // Update all fields that is not null.
    // Created to still be used if more fields is added to Account class.
    public Optional<Account> updateAccount(Account account)  {
        Optional<Account> optionalAccount = accountRepository.findById(account.getId());

        if (optionalAccount.isEmpty()) {
            return Optional.empty();
        }

        Account updatedAccount = optionalAccount.get();

        for (Field declaredField : account.getClass().getDeclaredFields()) {
            declaredField.setAccessible(true);
            try {
                if (declaredField.get(account) != null) {
                    System.out.println(declaredField.get(account));
                    declaredField.set(updatedAccount, declaredField.get(account));
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }

        return Optional.of(accountRepository.save(updatedAccount));
    }
}
