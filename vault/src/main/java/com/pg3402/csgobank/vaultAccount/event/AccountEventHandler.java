package com.pg3402.csgobank.vaultAccount.event;

import com.pg3402.csgobank.vaultAccount.VaultAccount;
import com.pg3402.csgobank.vaultAccount.VaultAccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
public class AccountEventHandler {

    private final VaultAccountService vaultAccountService;

    @RabbitListener(queues = "${amqp.queue.account}")
    void handleAccountEvent(AccountEvent event) {

        if (event.getEventType() == AccountEventEnum.DELETED) {
            log.info("Account with id " + event.getAccountId() + "is deleted");
            try {
                VaultAccount vaultAccount = new VaultAccount();
                vaultAccount.setId(event.getAccountId());
                vaultAccountService.delete(vaultAccount);
            } catch (final Exception e) {
                log.error("Error when trying to process AccountDeletedEvent", e);
                // Avoids the event to be re-queued and reprocessed.
                throw new AmqpRejectAndDontRequeueException(e);
            }
        }
        if (event.getEventType() == AccountEventEnum.CREATED) {
            log.info("Account with id " + event.getAccountId() + "is created");

            try {
                VaultAccount vaultAccount = new VaultAccount();
                vaultAccount.setId(event.getAccountId());
                vaultAccountService.save(vaultAccount);
            } catch (final Exception e) {
                log.error("Error when trying to process AccountCreatedEvent", e);
                // Avoids the event to be re-queued and reprocessed.
                throw new AmqpRejectAndDontRequeueException(e);
            }
        }

    }

}

