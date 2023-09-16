package com.pg3402.csgobank.transactionhistory.history;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
public class TransactionHistoryEventHandler {

    @RabbitListener(queues = "${amqp.queue.transactionHistory}")
    void handleMultiplicationSolved(TransactionHistoryEvent event) {
        log.info("Buyer ID:{ {} }", event.getBuyerID());
        log.info("Seller ID:{ {} }", event.getSellerID());
        try {
            System.out.println("Stuff");
        } catch (final Exception e) {
            log.error("Error when trying to process ChallengeSolvedEvent", e);
            // Avoids the event to be re-queued and reprocessed.
            throw new AmqpRejectAndDontRequeueException(e);
        }
    }
}

