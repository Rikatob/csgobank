package com.pg3402.csgobank.transactionValidator.transaction;

import com.pg3402.csgobank.transactionValidator.transaction.event.TransactionEventPub;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
public class TransactionService {


    private final VaultClient vaultClient;
    private final AccountClient accountClient;
    private final TransactionRepository transactionRepository;
    private final TransactionEventPub transactionEventPub;

    @Autowired
    public TransactionService(VaultClient vaultClient, AccountClient accountClient, TransactionRepository transactionRepository, TransactionEventPub transactionEventPub) {
        this.vaultClient = vaultClient;
        this.accountClient = accountClient;
        this.transactionRepository = transactionRepository;
        this.transactionEventPub = transactionEventPub;
    }

    public Transaction validateTransaction(Transaction transaction) {
        log.info("Validating transaction");
        if (checkTransaction(transaction)) {
            transaction.setState(TransactionState.VALIDATED);
        } else {
            transaction.setState(TransactionState.FAILED);
        }
        return transaction;
    }

    public Transaction validateOffer(Transaction transaction) {
        if (checkTransaction(transaction)) {
            transaction.setState(TransactionState.PENDING);
        }
        return transaction;
    }

    private boolean checkTransaction(Transaction transaction) {

        long itemId = transaction.getItemId();
        long fromVaultId = transaction.getFromVaultId();
        long toVaultId = transaction.getToVaultId();

        ResponseEntity<Boolean> fromVaultExistsResponse = vaultClient.checkIfVaultExists(fromVaultId);
        ResponseEntity<Boolean> toVaultExistsResponse = vaultClient.checkIfVaultExists(toVaultId);
        ResponseEntity<Long> itemOwnerResponse = vaultClient.getVaultId(itemId);

        if(transactionRepository.existsByItemId(itemId)){
            log.info("Item in a pending transaction");
            return false;
        }

        if(transaction.getFromAccountId() == transaction.getToAccountId()){
            return false;
        }

        if (transaction.getType() == TransactionType.BUY || transaction.getType() == TransactionType.SELL) {

            Optional<Integer> optionalInteger = getAccountCredits(transaction.getFromAccountId());

            if (optionalInteger.orElse(0) < transaction.getPrice()) {
                log.info("Not enough credits");
                return false;
            }

        }
        // Vault fromVault dont exists.
        if (Objects.equals(fromVaultExistsResponse.getBody(), false)) {

            log.info("Could not validate, fromVault dont exist");
            return false;
        }
        // Vault toVault dont exists.
        if (Objects.equals(toVaultExistsResponse.getBody(), false)) {

            log.info("Could not validate, toVault dont exist");
            return false;
        }
        // Request to get vaultId from itemId fails.
        if (itemOwnerResponse.getStatusCode() != HttpStatus.OK) {

            log.info("Could not validate, request failed");
            return false;
        }
        // Item is not present in the vault.
        if (!Objects.equals(itemOwnerResponse.getBody(), fromVaultId)) {

            log.info("Could not validate, fromVault dont have item");
            return false;
        }

        log.info("Transaction successfully validated");
        return true;
    }

    public List<Transaction> getIncomingTransaction(long id) {
        log.info("Getting all incoming transaction for AccountId: " + id);
        List<Transaction> buy = transactionRepository.findAllByFromAccountIdAndType(id, TransactionType.BUY);
        List<Transaction> sell = transactionRepository.findAllByToAccountIdAndType(id, TransactionType.SELL);
        List<Transaction> incoming = new ArrayList<>();
        incoming.addAll(buy);
        incoming.addAll(sell);
        return incoming;
    }

    public List<Transaction> getOutgoingTransaction(long id) {
        log.info("Getting all outgoing transaction for AccountId: " + id);
        List<Transaction> sell = transactionRepository.findAllByFromAccountIdAndType(id, TransactionType.SELL);
        List<Transaction> buy = transactionRepository.findAllByToAccountIdAndType(id, TransactionType.BUY);
        List<Transaction> outgoing = new ArrayList<>();
        outgoing.addAll(sell);
        outgoing.addAll(buy);
        return outgoing;
    }

    public Optional<Transaction> acceptOffer(long transactionId) {

        Optional<Transaction> optionalTransaction = transactionRepository.findById(transactionId);

        if (optionalTransaction.isEmpty()) {
            log.info("Transaction not found");
            return Optional.empty();
        }

        Transaction transaction = optionalTransaction.get();

        Optional<Integer> optionalInteger = getAccountCredits(transaction.getFromAccountId());

        if (optionalInteger.orElse(0) < transaction.getPrice()) {
            log.info("Not enough credits");
            return Optional.empty();
        }

        log.info("Transaction " + transaction + " is accepted ");
        transaction.setState(TransactionState.ACCEPTED);
        transactionEventPub.publishTransaction(transaction);
        transactionRepository.delete(transaction);


        return Optional.of(transaction);
    }

    public Optional<Transaction> declineOffer(long transactionId){
        Optional<Transaction> optionalTransaction = transactionRepository.findById(transactionId);

        if (optionalTransaction.isEmpty()) {
            log.info("Transaction not found");
            return Optional.empty();
        }

        Transaction transaction = optionalTransaction.get();
        transaction.setState(TransactionState.DECLINED);
        transactionRepository.delete(transaction);

        return Optional.of(transaction);
    }

    private Optional<Integer> getAccountCredits(long accountId) {
        ResponseEntity<Integer> accountCreditResponse = accountClient.getAccountCredit(accountId);
        Integer accountCredits = accountCreditResponse.getBody();
        if (accountCredits == null) {
            log.info("Something went wrong credits not found");
            return Optional.empty();
        }
        return Optional.of(accountCredits);
    }
}
