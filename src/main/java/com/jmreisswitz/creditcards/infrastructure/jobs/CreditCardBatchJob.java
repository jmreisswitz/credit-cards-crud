package com.jmreisswitz.creditcards.infrastructure.jobs;

import com.jmreisswitz.creditcards.application.ProcessBatchesService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class CreditCardBatchJob {

    private final ProcessBatchesService processBatchesService;

    public CreditCardBatchJob(ProcessBatchesService processBatchesService) {
        this.processBatchesService = processBatchesService;
    }

    @Scheduled(cron = "0 * * * * ?")
    public void run() {
        processBatchesService.processNotCompletedBatches();
    }
}
