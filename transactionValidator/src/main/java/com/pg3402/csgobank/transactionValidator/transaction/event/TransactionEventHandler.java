package com.pg3402.csgobank.transactionValidator.transaction.event;

import com.pg3402.csgobank.transactionValidator.transaction.Transaction;
import com.pg3402.csgobank.transactionValidator.transaction.TransactionRepository;
import com.pg3402.csgobank.transactionValidator.transaction.TransactionService;
import com.pg3402.csgobank.transactionValidator.transaction.TransactionState;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class TransactionEventHandler {
    private final TransactionService transactionService;
    private final TransactionRepository transactionRepository;

    public TransactionEventHandler(TransactionService transactionService, TransactionRepository transactionRepository) {
        this.transactionService = transactionService;
        this.transactionRepository = transactionRepository;
    }



    @RabbitListener(queues = "${amqp.queue.transaction}")
    void handleTransactionHistoryEvent(TransactionEvent event) {
        try {
            Transaction transaction = buildTransaction(event);
            log.info(transaction + " is successfully received");
            transaction = transactionService.validateOffer(transaction);
            if(transaction.getState().equals(TransactionState.PENDING)){
                transactionRepository.save(transaction);
            } else {
                log.info(transaction + "failed");
            }

        } catch (final Exception e) {
            log.error("Error when trying to process TransactionEvent", e);
            // Avoids the event to be re-queued and reprocessed.
            throw new AmqpRejectAndDontRequeueException(e);
        }
    }

    private Transaction buildTransaction( TransactionEvent transactionEvent){

        Transaction transaction = new Transaction();

        transaction.setItemId(transactionEvent.getItemId());
        transaction.setPrice(transactionEvent.getPrice());

        transaction.setFromVaultId(transactionEvent.getFromVaultId());
        transaction.setToVaultId(transactionEvent.getToVaultId());

        transaction.setFromAccountId(transactionEvent.getFromAccountId());
        transaction.setToAccountId(transactionEvent.getToAccountId());

        transaction.setType(transactionEvent.getType());
        transaction.setState(transactionEvent.getState());

        return transaction;
    }
}

