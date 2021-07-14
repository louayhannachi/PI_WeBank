package com.esprit.weBank.controller;

import com.esprit.weBank.currency.CurrencyConverterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.money.MonetaryAmount;
import javax.money.NumberValue;
import java.math.BigDecimal;

@RestController
public class CurrencyConverterController {
    @Autowired
    CurrencyConverterService currencyConverterService;
    @PostMapping(value ="/convert")
    @ResponseBody
    public NumberValue convert(@RequestParam String currentCurrency, String targetCurrency, BigDecimal amount) {
        return   currencyConverterService.convert(currentCurrency,targetCurrency,amount);
    }
}
