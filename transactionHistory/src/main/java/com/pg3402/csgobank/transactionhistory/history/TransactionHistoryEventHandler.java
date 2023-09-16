package com.pg3402.csgobank.transactionhistory.history;

import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Service
public class TransactionHistoryEventHandler {

    TransactionHistoryService transactionHistoryService;
    @RabbitListener(queues = "${amqp.queue.transactionHistory}")
    void handleMultiplicationSolved(final TransactionHistoryEvent event) {
        log.info("Challenge Solved Event received: {}", event.getAttemptId());
        try {
            transactionHistoryService.newAttemptForUser(event);
        } catch (final Exception e) {
            log.error("Error when trying to process ChallengeSolvedEvent", e);
            // Avoids the event to be re-queued and reprocessed.
            throw new AmqpRejectAndDontRequeueException(e);
        }
    }
}
