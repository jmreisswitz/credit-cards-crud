package unit.com.jmreisswitz.creditcards.domain.creditcard.batch;

import com.jmreisswitz.creditcards.domain.creditcard.*;
import com.jmreisswitz.creditcards.domain.creditcard.batch.CreditCardBatch;
import com.jmreisswitz.creditcards.domain.creditcard.batch.CreditCardBatchLineStatus;
import com.jmreisswitz.creditcards.domain.creditcard.validator.CreditCardNumberValidator;
import com.jmreisswitz.creditcards.domain.user.UserId;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;

class CreditCardBatchTest {

    @Test
    void changeInvalidLinesToInvalidStatus() {
         CreditCardNumberValidator validator = mock(CreditCardNumberValidator.class);
        CreditCardNumber invalidCreditCardNumber = new CreditCardNumber("123412341234");
        CreditCardBatch creditCardBatch = new CreditCardBatch(
                 mock(UserId.class), "name", LocalDate.now(), List.of(
                new CreditCardBatch.Line("1", mock(CreditCardNumber.class),
                        "1234", CreditCardBatchLineStatus.TO_BE_PROCESSED),
                new CreditCardBatch.Line("2", invalidCreditCardNumber,
                        "1234", CreditCardBatchLineStatus.TO_BE_PROCESSED)
                 ));
        doThrow(new InvalidCreditCardDataException("Invalid credit card number"))
                .when(validator).validate(invalidCreditCardNumber);

        creditCardBatch.validateLines(validator);

        List<CreditCardBatch.Line> lines = new ArrayList<>(creditCardBatch.creditCards());
        assertThat(lines, hasSize(2));
        assertThat(lines.getFirst().status(), is(CreditCardBatchLineStatus.TO_BE_PROCESSED));
        assertThat(lines.getLast().status(), is(CreditCardBatchLineStatus.INVALID));
    }

    @Test
    void isCompletedWhenAllLinesAreProcessedOrInvalids() {
        CreditCardBatch creditCardBatch = new CreditCardBatch(
                mock(UserId.class), "name", LocalDate.now(), List.of(
                new CreditCardBatch.Line("1", mock(CreditCardNumber.class), "1234", CreditCardBatchLineStatus.PROCESSED),
                new CreditCardBatch.Line("2", mock(CreditCardNumber.class), "1234", CreditCardBatchLineStatus.INVALID)
                ));

        boolean isCompleted = creditCardBatch.isCompleted();

        assertThat(isCompleted, is(true));
    }

    @Test
    void isNotCompletedWhenNotAllLinesAreProcessed() {
        CreditCardBatch creditCardBatch = new CreditCardBatch(
                mock(UserId.class), "name", LocalDate.now(), List.of(
                new CreditCardBatch.Line("1", mock(CreditCardNumber.class), "1234", CreditCardBatchLineStatus.PROCESSED),
                new CreditCardBatch.Line("2", mock(CreditCardNumber.class), "1234", CreditCardBatchLineStatus.TO_BE_PROCESSED)
                ));

        boolean isCompleted = creditCardBatch.isCompleted();

        assertThat(isCompleted, is(false));
    }

    @Test
    void processLinesToBeProcessed() {
        CreditCardBatch.Line processedLine = mock(CreditCardBatch.Line.class);
        CreditCardBatch.Line invalidLine = mock(CreditCardBatch.Line.class);
        CreditCardBatch.Line notProcessedLine = mock(CreditCardBatch.Line.class);
        UserId userId = mock(UserId.class);
        CreditCardBatch creditCardBatch = new CreditCardBatch(
                userId, "name", LocalDate.now(),
                List.of(processedLine, notProcessedLine, invalidLine));
        when(processedLine.status()).thenReturn(CreditCardBatchLineStatus.PROCESSED);
        when(invalidLine.status()).thenReturn(CreditCardBatchLineStatus.INVALID);
        when(notProcessedLine.status()).thenReturn(CreditCardBatchLineStatus.TO_BE_PROCESSED);
        SaveCreditCardDomainService service = mock(SaveCreditCardDomainService.class);

        creditCardBatch.process(service);

        verify(notProcessedLine).process(userId, service);
        verify(processedLine, never()).process(userId, service);
        verify(invalidLine, never()).process(userId, service);
    }

    @Test
    void processLineDelegateToServiceAndUpdateAsProcessed() {
        CreditCardBatch.Line line = new CreditCardBatch.Line("1", mock(CreditCardNumber.class), "1234",
                CreditCardBatchLineStatus.TO_BE_PROCESSED);
        UserId userId = mock(UserId.class);
        SaveCreditCardDomainService service = mock(SaveCreditCardDomainService.class);

        line.process(userId, service);

        verify(service).save(any(CreditCard.class));
        assertThat(line.status(), is(CreditCardBatchLineStatus.PROCESSED));
    }

}