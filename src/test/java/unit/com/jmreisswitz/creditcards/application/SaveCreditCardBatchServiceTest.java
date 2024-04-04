package unit.com.jmreisswitz.creditcards.application;

import com.jmreisswitz.creditcards.application.SaveCreditCardBatchService;
import com.jmreisswitz.creditcards.domain.creditcard.batch.CreditCardBatch;
import com.jmreisswitz.creditcards.domain.creditcard.batch.CreditCardBatchRepository;
import com.jmreisswitz.creditcards.domain.creditcard.validator.CreditCardNumberValidator;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class SaveCreditCardBatchServiceTest {

    CreditCardBatchRepository creditCardBatchRepository = mock(CreditCardBatchRepository.class);
    CreditCardNumberValidator creditCardNumberValidator = mock(CreditCardNumberValidator.class);
    SaveCreditCardBatchService saveCreditCardBatchService =
            new SaveCreditCardBatchService(creditCardBatchRepository, creditCardNumberValidator);

    @Test
    void delegateToRepositoryAfterValidating() {
         CreditCardBatch creditCardBatch = mock(CreditCardBatch.class);

         saveCreditCardBatchService.saveCreditCardBatch(creditCardBatch);

          verify(creditCardBatch).validateLines(creditCardNumberValidator);
          verify(creditCardBatchRepository).save(creditCardBatch);
    }

}