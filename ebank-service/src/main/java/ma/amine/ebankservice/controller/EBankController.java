package ma.amine.ebankservice.controller;

import lombok.RequiredArgsConstructor;
import ma.amine.ebankservice.entity.BankAccount;
import ma.amine.ebankservice.service.EBankService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class EBankController {
    private final EBankService eBankService;

    @GetMapping("/accounts")
    public List<BankAccount> getAllBankAccounts() {
        return eBankService.getAllBankAccounts();
    }

    @GetMapping("/accounts/{id}")
    public BankAccount getBankAccountById(@PathVariable String id) {
        return eBankService.getBankAccountById(id);
    }

    @PostMapping("/accounts")
    public BankAccount saveBankAccount(@RequestBody BankAccount bankAccount) {
        return eBankService.saveBankAccount(bankAccount);
    }
}
