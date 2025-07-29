package bankAppEmulation.bankApp.service;

import bankAppEmulation.bankApp.dto.BankAccountRequest;
import bankAppEmulation.bankApp.dto.BankAccountResponse;
import bankAppEmulation.bankApp.entity.BankAccount;
import bankAppEmulation.bankApp.exception.NotFoundException;
import bankAppEmulation.bankApp.nuban.Nuban;
import bankAppEmulation.bankApp.repo.BankAccountRepo;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class BankAccountService {
    private final BankAccountRepo repo;

    public BankAccountService(BankAccountRepo repo) {
        this.repo = repo;
    }

    public BankAccountResponse createAccount(BankAccountRequest req) {
        String serial = generateRandomSerial();
        String accountNumber = Nuban.generateNuban(req.getBankCode(), serial);

        BankAccount account = new BankAccount();
        account.setBankCode(req.getBankCode());
        account.setAccountNumber(accountNumber);
        account.setAccountName(req.getAccountName());

        repo.save(account);

        return mapToResponse(account);
    }

    public BankAccountResponse getByAccountNumber(String accountNumber) {
        BankAccount account = repo.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new NotFoundException("Account not found"));
        return mapToResponse(account);
    }

    public boolean validateAccount(String accountNumber, String bankCode) {
        return Nuban.validateNuban(accountNumber, bankCode);
    }

    private String generateRandomSerial() {
        Random rand = new Random();
        return String.format("%09d", rand.nextInt(1_000_000_000));
    }

    private BankAccountResponse mapToResponse(BankAccount acc) {
        BankAccountResponse res = new BankAccountResponse();
        res.setAccountNumber(acc.getAccountNumber());
        res.setBankCode(acc.getBankCode());
        res.setAccountName(acc.getAccountName());
        return res;
    }
}
