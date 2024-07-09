package com.cursosdedesarrollo.apirestjpa.entities.herencia.simple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/accounts")
public class AccountController {

    @Autowired
    private CreditAccountRepository creditAccountRepository;
    @Autowired
    private DebitAccountRepository debitAccountRepository;

    @GetMapping("/credit/")
    public List<CreditAccount> getCredits(){
        return this.creditAccountRepository.findAll();
    }

    @GetMapping("/debit/")
    public List<DebitAccount> getDebits(){
        return this.debitAccountRepository.findAll();
    }

    @PostMapping("/credit/")
    public CreditAccount addCredit(
            @RequestBody CreditAccount creditAccount
    ){
        this.creditAccountRepository.save(creditAccount);
        return creditAccount;
    }

    @PostMapping("/debit/")
    public DebitAccount addDebit(
            @RequestBody DebitAccount debitAccount
    ){
        this.debitAccountRepository.save(debitAccount);
        return debitAccount;
    }
}
