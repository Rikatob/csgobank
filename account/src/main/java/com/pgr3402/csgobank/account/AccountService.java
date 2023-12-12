package com.pgr3402.csgobank.account;


import com.pgr3402.csgobank.account.event.AccountEventEnum;
import com.pgr3402.csgobank.account.event.AccountEventPub;
import com.pgr3402.csgobank.transaction.Transaction;
import com.pgr3402.csgobank.transaction.TransactionState;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.Optional;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final AccountEventPub accountEventPub;

    public AccountService(AccountRepository accountRepository, AccountEventPub accountEventPub) {
        this.accountRepository = accountRepository;
        this.accountEventPub = accountEventPub;
    }


    public void deleteAccount(Account account){
        long accountId = account.getId();
         accountRepository.delete(account);
         accountEventPub.publishAccountEvent(accountId,AccountEventEnum.DELETED);
    }

    public Account createAccount(Account account) {
        account = accountRepository.save(account);
        accountEventPub.publishAccountEvent(account, AccountEventEnum.CREATED);
        return account;
    }

    public boolean exists(Account account) {
        return accountRepository.findByEmail(account.getEmail()).isPresent();
    }

    public Optional<Account> findById(long id) {
        return accountRepository.findById(id);
    }


    // Update all fields that is not null.
    // Created to still be used if more fields is added to Account class.
    public Optional<Account> updateAccount(Account account) {
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

    public Transaction transferCredits(Transaction transaction){
        Optional<Account> optionalFromAccount = accountRepository.findById(transaction.getFromAccountId());
        Optional<Account> optionalToAccount = accountRepository.findById(transaction.getToAccountId());

        if(optionalFromAccount.isEmpty() || optionalToAccount.isEmpty()){
            transaction.setState(TransactionState.FAILED);

            return transaction;
        }

        Account fromAccount = optionalFromAccount.get();
        Account toAccount = optionalToAccount.get();

        fromAccount.withdraw(transaction.getPrice());
        toAccount.deposit(transaction.getPrice());


        transaction.setState(TransactionState.COMPLETE);
        return transaction;
    }


}
