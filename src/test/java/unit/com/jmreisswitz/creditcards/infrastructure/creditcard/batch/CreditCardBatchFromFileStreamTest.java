package unit.com.jmreisswitz.creditcards.infrastructure.creditcard.batch;


import com.jmreisswitz.creditcards.domain.creditcard.batch.CreditCardBatch;
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
        try (FileInputStream inputStream = new FileInputStream("src/test/java/resources/creditcardbatch/batch.txt")) {
            CreditCardBatchFromFileStream creditCardBatchFromFileStream = new CreditCardBatchFromFileStream(inputStream);
            creditCardBatch = creditCardBatchFromFileStream.readFor(new UserId(1L));
        }

        assertThat(creditCardBatch, is(notNullValue()));
        assertThat(creditCardBatch.name().trim(), is("DESAFIO-HYPERATIVA"));
        assertThat(creditCardBatch.date(), is(LocalDate.of(2018, 5, 24)));
        assertThat(creditCardBatch.creditCards(), hasSize(2));
    }



}