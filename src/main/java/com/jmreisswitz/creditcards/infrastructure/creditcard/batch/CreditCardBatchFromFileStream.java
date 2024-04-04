package com.jmreisswitz.creditcards.infrastructure.creditcard.batch;

import com.jmreisswitz.creditcards.domain.creditcard.CreditCardNumber;
import com.jmreisswitz.creditcards.domain.creditcard.batch.CreditCardBatch;
import com.jmreisswitz.creditcards.domain.user.UserId;

import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;


public class CreditCardBatchFromFileStream {

    public static final int TITTLE_LENGTH = 28;
    public static final int DATE_LENGTH = 8;
    public static final int LINE_ID_LENGTH = 5;
    public static final int CREDIT_CARD_NUMBER_LENGTH = 16;
    private final Scanner scanner;

    public CreditCardBatchFromFileStream(InputStream inputStream) {
        scanner = new Scanner(inputStream);
    }

    public CreditCardBatch readFor(UserId userId) {
        String firstLine = scanner.nextLine();
        return new CreditCardBatch(
                userId,
                name(firstLine),
                date(firstLine),
                creditCards()
        );
    }

    private LocalDate date(String firstLine) {
        String dateAsString = firstLine.substring(TITTLE_LENGTH + 1, TITTLE_LENGTH + DATE_LENGTH + 1);
        return LocalDate.parse(dateAsString, DateTimeFormatter.BASIC_ISO_DATE);
    }

    private String name(String firstLine) {
        return firstLine.substring(0, TITTLE_LENGTH).trim();
    }

    private Collection<CreditCardBatch.Line> creditCards() {
        List<CreditCardBatch.Line> creditCards = new ArrayList<>();
        while (scanner.hasNext()) {
            processCreditCardLine(creditCards);
        }
        return creditCards;
    }

    private void processCreditCardLine(List<CreditCardBatch.Line> creditCards) {
        String line = scanner.nextLine();
        if (line.startsWith("LOTE")) {
            return;
        }
        CreditCardBatch.Line creditCardLine = creditCardLine(line);
        creditCards.add(creditCardLine);
    }

    private CreditCardBatch.Line creditCardLine(String line) {
        String identifier = line.substring(0, LINE_ID_LENGTH).trim();
        String creditCardNumber = line.substring(LINE_ID_LENGTH + 1,
                LINE_ID_LENGTH + CREDIT_CARD_NUMBER_LENGTH + 1).trim();
        return new CreditCardBatch.Line(identifier, new CreditCardNumber(creditCardNumber));
    }

}
