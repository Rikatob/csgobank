package com.pg3402.csgobank.transactionValidator.transaction;


import io.micrometer.tracing.Span;
import io.micrometer.tracing.Tracer;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;
    @Autowired
    private Tracer tracer;

    @GetMapping
    public @ResponseBody boolean validateTransaction(
            @RequestHeader("traceId") String traceId,
            @RequestHeader("spanId") String spanId) {

        Span span = tracer.nextSpan().name("validate-transaction-span").start();
        MDC.put("spanId",span.context().spanId());
        MDC.put("traceId",traceId);

        boolean validated;

        System.out.println(traceId+"   "+ spanId);
        try (Tracer.SpanInScope ws = tracer.withSpan(span)) {
            log.warn("Validating transaction. (Controller");
            validated = transactionService.validateTransaction();
            log.warn("Done with validating transaction.(Controller)");
        } finally {
            MDC.clear();
            span.end();
        }

        return validated;
    }
}
