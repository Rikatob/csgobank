package com.pg3402.csgobank.transaction;


import com.pg3402.csgobank.vault.VaultService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class TransactionEventHandler {


    private final VaultService vaultService;

    public TransactionEventHandler(VaultService vaultService) {
        this.vaultService = vaultService;
    }

    @RabbitListener(queues = "${amqp.queue.transaction}")
    void handleTransactionHistoryEvent(TransactionEvent event) {
        try {
            log.info("Transaction incoming!! handle tim!");
          Transaction transaction = buildTransaction(event);

           vaultService.handleTransaction(transaction);

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

