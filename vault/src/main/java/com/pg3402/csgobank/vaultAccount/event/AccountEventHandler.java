package com.pg3402.csgobank.vaultAccount.event;

import com.pg3402.csgobank.vaultAccount.VaultAccount;
import com.pg3402.csgobank.vaultAccount.VaultAccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
public class AccountEventHandler {

    private final VaultAccountService vaultAccountService;

    @RabbitListener(queues = "${amqp.queue.account}")
    void handleMultiplicationSolved(AccountEvent event) {
        System.out.println("event: " + event);
        VaultAccount vaultAccount = new VaultAccount();
        vaultAccount.setId(event.getAccountId());
        vaultAccountService.save(vaultAccount);
        try {
            System.out.println("Stuff");
        } catch (final Exception e) {
            log.error("Error when trying to process ChallengeSolvedEvent", e);
            // Avoids the event to be re-queued and reprocessed.
            throw new AmqpRejectAndDontRequeueException(e);
        }
    }
}

