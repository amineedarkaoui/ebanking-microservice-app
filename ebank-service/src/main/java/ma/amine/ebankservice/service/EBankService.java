package ma.amine.ebankservice.service;

import lombok.RequiredArgsConstructor;
import ma.amine.ebankservice.client.CustomerRestClient;
import ma.amine.ebankservice.entity.BankAccount;
import ma.amine.ebankservice.model.Customer;
import ma.amine.ebankservice.repository.BankAccountRepository;
import org.springaicommunity.mcp.annotation.McpTool;
import org.springaicommunity.mcp.annotation.McpToolParam;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EBankService {
    private final BankAccountRepository accountRepository;
    private final CustomerRestClient customerRestClient;

    @McpTool(description = "Get all bank accounts")
    public List<BankAccount> getAllBankAccounts() {
        return accountRepository.findAll();
    }

    @McpTool(description = "Get a bank account by ID")
    public BankAccount getBankAccountById(
            @McpToolParam(description = "The bank account ID") String id
    ) {
        BankAccount bankAccount = accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Account not found"));
        Customer customer = customerRestClient.getCustomerById(bankAccount.getCustomerId());
        bankAccount.setCustomer(customer);
        return bankAccount;
    }

    @McpTool(description = "Save a new bank account")
    public BankAccount saveBankAccount(
            @McpToolParam(description = "BankAccount object containing balance, type (CURRENT-ACCOUNT or SAVING-ACCOUNT), and customerId") BankAccount bankAccount
    ) {
        try {
            Customer customer = customerRestClient.getCustomerById(bankAccount.getCustomerId());
            bankAccount.setId(UUID.randomUUID().toString());
            bankAccount.setCreatedAt(new Date());
            bankAccount.setCustomer(customer);
            return accountRepository.save(bankAccount);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}