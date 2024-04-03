package com.jmreisswitz.creditcards.infrastructure.controller;


import com.jmreisswitz.creditcards.application.SaveCreditCardService;
import com.jmreisswitz.creditcards.domain.creditcard.CreditCard;
import com.jmreisswitz.creditcards.domain.user.UserId;
import com.jmreisswitz.creditcards.infrastructure.controller.request.SaveCreditCardRequest;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/credit-card")
public class CreditCardController {

    private final SaveCreditCardService saveCreditCardService;

    public CreditCardController(SaveCreditCardService saveCreditCardService) {
        this.saveCreditCardService = saveCreditCardService;
    }

    @PostMapping
    public void saveCreditCard(@AuthenticationPrincipal UserDetails userDetails,
                               @RequestBody SaveCreditCardRequest saveCreditCardRequest) {
        var creditCard = new CreditCard(
                new UserId(1L),  // TODO: get user id from userDetails
                saveCreditCardRequest.asCreditCardData()
        );
        saveCreditCardService.save(creditCard);
    }


}
