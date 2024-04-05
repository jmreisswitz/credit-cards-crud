package com.jmreisswitz.creditcards.infrastructure.jobs;

import com.jmreisswitz.creditcards.application.ProcessBatchesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class CreditCardBatchJob {

    private static final Logger logger = LoggerFactory.getLogger(CreditCardBatchJob.class);
    private final ProcessBatchesService processBatchesService;

    public CreditCardBatchJob(ProcessBatchesService processBatchesService) {
        this.processBatchesService = processBatchesService;
    }

    @Scheduled(cron = "0 * * * * ?")
    public void run() {
        logger.info("Running CreditCardBatchJob");
        processBatchesService.processNotCompletedBatches();
    }
}
