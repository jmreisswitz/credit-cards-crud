package unit.com.jmreisswitz.creditcards.infrastructure.creditcard.batch;


import com.jmreisswitz.creditcards.domain.creditcard.CreditCardNumber;
import com.jmreisswitz.creditcards.domain.creditcard.batch.CreditCardBatch;
import com.jmreisswitz.creditcards.domain.creditcard.batch.CreditCardBatchLineStatus;
import com.jmreisswitz.creditcards.domain.user.UserId;
import com.jmreisswitz.creditcards.infrastructure.creditcard.batch.CreditCardBatchFromFileStream;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class CreditCardBatchFromFileStreamTest {

    @Test
    void createFromFile() throws IOException {
        CreditCardBatch creditCardBatch;
        try (FileInputStream inputStream = new FileInputStream("src/test/resources/credit-card-batch/batch.txt")) {
            CreditCardBatchFromFileStream creditCardBatchFromFileStream =
                    new CreditCardBatchFromFileStream(inputStream);
            creditCardBatch = creditCardBatchFromFileStream.readFor(new UserId(1));
        }

        assertThat(creditCardBatch, is(notNullValue()));
        assertThat(creditCardBatch.name().trim(), is("DESAFIO-HYPERATIVA"));
        assertThat(creditCardBatch.date(), is(LocalDate.of(2018, 5, 24)));
        assertThat(creditCardBatch.creditCards(), hasSize(2));
        var line = (CreditCardBatch.Line) creditCardBatch.creditCards().toArray()[0];
        assertThat(line.status(), is(CreditCardBatchLineStatus.TO_BE_PROCESSED));
        assertThat(line.creditCardNumber(), is(new CreditCardNumber("1234123412341234")));
        assertThat(line.identifier(), is("C2"));
        assertThat(line.lastFourDigits(), is("1234"));
    }



}