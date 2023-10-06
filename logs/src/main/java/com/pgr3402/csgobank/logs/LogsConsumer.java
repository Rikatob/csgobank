package com.pgr3402.csgobank.logs;

import org.slf4j.MDC;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.stereotype.Service;

import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class LogsConsumer {

    @RabbitListener(queues = "logs.queue")
    public void log(final String msg,
                    @Header("level") String level,
                    @Header("amqp_appId") String appId,
                    @Header(value = "traceId", required = false) String traceId,
                    @Header(value = "spanId", required = false) String spanId) {
        Marker marker = MarkerFactory.getMarker(appId);

        // Check if traceId header is present before using it.
        if (traceId == null) {
            traceId = "N/A"; // Provide a default value or handle the absence accordingly.
        }
        MDC.put("traceId", traceId);

        // Same for spanId.
        if (spanId == null) {
            spanId = "N/A";
        }
        MDC.put("spanId", spanId);

        try {
            switch (level) {
                case "INFO" -> log.info(marker, msg);
                case "ERROR" -> log.error(marker, msg);
                case "WARN" -> log.warn(marker, msg);
            }
            // Clean up MDC after logging
        } finally {
            MDC.remove("traceId");
            MDC.remove("spanId");
        }
    }
}