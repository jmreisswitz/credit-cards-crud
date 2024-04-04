package unit.com.jmreisswitz.creditcards.domain.creditcard.batch;

import com.jmreisswitz.creditcards.domain.creditcard.CreditCardNumber;
import com.jmreisswitz.creditcards.domain.creditcard.InvalidCreditCardDataException;
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
                         new CreditCardBatch.Line("1", mock(CreditCardNumber.class), CreditCardBatchLineStatus.TO_BE_PROCESSED),
                         new CreditCardBatch.Line("2", invalidCreditCardNumber, CreditCardBatchLineStatus.TO_BE_PROCESSED)
                 ));
        doThrow(new InvalidCreditCardDataException("Invalid credit card number"))
                .when(validator).validate(invalidCreditCardNumber);

        creditCardBatch.validateLines(validator);

        List<CreditCardBatch.Line> lines = new ArrayList<>(creditCardBatch.creditCards());
        assertThat(lines, hasSize(2));
        assertThat(lines.getFirst().status(), is(CreditCardBatchLineStatus.TO_BE_PROCESSED));
        assertThat(lines.getLast().status(), is(CreditCardBatchLineStatus.INVALID));
    }

}