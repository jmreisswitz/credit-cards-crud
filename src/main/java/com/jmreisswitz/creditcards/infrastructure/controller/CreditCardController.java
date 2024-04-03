package com.jmreisswitz.creditcards.infrastructure.controller;


import com.jmreisswitz.creditcards.application.RetrieveCreditCardService;
import com.jmreisswitz.creditcards.application.SaveCreditCardService;
import com.jmreisswitz.creditcards.domain.creditcard.CreditCard;
import com.jmreisswitz.creditcards.domain.creditcard.InvalidCreditCardDataException;
import com.jmreisswitz.creditcards.infrastructure.controller.request.SaveCreditCardRequest;
import com.jmreisswitz.creditcards.infrastructure.controller.response.CardCreatedResponse;
import com.jmreisswitz.creditcards.infrastructure.controller.response.CreditCardView;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/credit-card")
public class CreditCardController {

    private final SaveCreditCardService saveCreditCardService;
    private final RetrieveCreditCardService retrieveCreditCardService;

    public CreditCardController(SaveCreditCardService saveCreditCardService,
                                RetrieveCreditCardService retrieveCreditCardService) {
        this.saveCreditCardService = saveCreditCardService;
        this.retrieveCreditCardService = retrieveCreditCardService;
    }

    @PostMapping
    public ResponseEntity<CardCreatedResponse> saveCreditCard(Principal principal,
                                                              @RequestBody SaveCreditCardRequest saveCreditCardRequest) {
        var creditCard = creditCardFrom(principal, saveCreditCardRequest);
        try {
            return tryToSave(creditCard);
        } catch (InvalidCreditCardDataException exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, exception.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<CreditCardView>> listUserCreditCards(Principal principal) {
        var userId = PrincipalMapper.userIdFrom(principal);
        var creditCards = retrieveCreditCardService.retrieveByUserId(userId);
        List<CreditCardView> creditCardViews = creditCards.stream()
                .map(CreditCardView::from)
                .toList();
        return ResponseEntity.ok(creditCardViews);
    }

    private ResponseEntity<CardCreatedResponse> tryToSave(CreditCard creditCard) {
        var savedCreditCard = saveCreditCardService.save(creditCard);
        return ResponseEntity.ok(new CardCreatedResponse(savedCreditCard.id().value()));
    }

    private static CreditCard creditCardFrom(Principal principal, SaveCreditCardRequest saveCreditCardRequest) {
        return new CreditCard(
                PrincipalMapper.userIdFrom(principal),
                saveCreditCardRequest.asCreditCardData()
        );
    }

}
