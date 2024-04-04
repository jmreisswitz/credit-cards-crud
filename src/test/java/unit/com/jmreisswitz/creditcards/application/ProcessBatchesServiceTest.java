package unit.com.jmreisswitz.creditcards.application;

import com.jmreisswitz.creditcards.application.ProcessBatchesService;
import com.jmreisswitz.creditcards.domain.creditcard.SaveCreditCardDomainService;
import com.jmreisswitz.creditcards.domain.creditcard.batch.CreditCardBatch;
import com.jmreisswitz.creditcards.domain.creditcard.batch.CreditCardBatchRepository;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.mockito.Mockito.*;

class ProcessBatchesServiceTest {

    private final CreditCardBatchRepository creditCardBatchRepository = mock(CreditCardBatchRepository.class);
    private final SaveCreditCardDomainService saveCreditCardDomainService = mock(SaveCreditCardDomainService.class);
    private final ProcessBatchesService processBatchesService =
            new ProcessBatchesService(creditCardBatchRepository, saveCreditCardDomainService);

    @Test
    void processNotCompletedBatchesAndSave() {
        CreditCardBatch creditCardBatch = mock(CreditCardBatch.class);
        when(creditCardBatchRepository.findNotCompleted()).thenReturn(List.of(creditCardBatch));

        processBatchesService.processNotCompletedBatches();

        verify(creditCardBatch).process(saveCreditCardDomainService);
        verify(creditCardBatchRepository).save(creditCardBatch);
    }

}