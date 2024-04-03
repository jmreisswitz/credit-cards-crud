package com.jmreisswitz.creditcards.infrastructure.controller;


import com.jmreisswitz.creditcards.application.RetrieveCreditCardService;
import com.jmreisswitz.creditcards.application.SaveCreditCardService;
import com.jmreisswitz.creditcards.domain.creditcard.CreditCard;
import com.jmreisswitz.creditcards.domain.creditcard.InvalidCreditCardDataException;
import com.jmreisswitz.creditcards.domain.user.UserId;
import com.jmreisswitz.creditcards.infrastructure.controller.request.SaveCreditCardRequest;
import com.jmreisswitz.creditcards.infrastructure.controller.response.CardCreatedResponse;
import com.jmreisswitz.creditcards.infrastructure.controller.response.CreditCardView;
import com.jmreisswitz.creditcards.infrastructure.controller.response.ResponseError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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
    public ResponseEntity<CardCreatedResponse> saveCreditCard(@AuthenticationPrincipal UserDetails userDetails,
                                                              @RequestBody SaveCreditCardRequest saveCreditCardRequest) {
        var creditCard = new CreditCard(
                new UserId(1L),  // TODO: get user id from userDetails
                saveCreditCardRequest.asCreditCardData()
        );
        try {
            var savedCreditCard = saveCreditCardService.save(creditCard);
            return ResponseEntity.ok(new CardCreatedResponse(savedCreditCard.id().value()));
        } catch (InvalidCreditCardDataException exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, exception.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<CreditCardView>> listUserCreditCards(@AuthenticationPrincipal UserDetails userDetails) {
        var userId = new UserId(1L);  // TODO: get user id from userDetails
        var creditCards = retrieveCreditCardService.retrieveByUserId(userId);
        List<CreditCardView> creditCardViews = creditCards.stream()
                .map(CreditCardView::from)
                .toList();
        return ResponseEntity.ok(creditCardViews);
    }

}
