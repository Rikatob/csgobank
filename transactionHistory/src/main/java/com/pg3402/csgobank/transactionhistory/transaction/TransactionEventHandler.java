package com.pg3402.csgobank.transactionhistory.transaction;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class TransactionEventHandler {

    private final TransactionRepository transactionRepository;

    @Autowired
    public TransactionEventHandler(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @RabbitListener(queues = "${amqp.queue.transactionHistory}")
    void handleTransactionHistoryEvent(TransactionEvent event) {

        try {
            Transaction transaction = buildTransaction(event);
            transaction = transactionRepository.save(transaction);
            log.info(transaction + " is successfully saved");

        } catch (final Exception e) {
            log.error("Error when trying to process TransactionEvent", e);
            // Avoids the event to be re-queued and reprocessed.
            throw new AmqpRejectAndDontRequeueException(e);
        }
    }

    private Transaction buildTransaction( TransactionEvent transactionEvent){

        Transaction transaction = new Transaction();
        transaction.setItemID(transactionEvent.getItemId());
        transaction.setFromVaultId(transactionEvent.getFromVaultId());
        transaction.setToVaultId(transactionEvent.getToVaultId());
        transaction.setCompleted(transactionEvent.isCompleted());

        return transaction;
    }
}

