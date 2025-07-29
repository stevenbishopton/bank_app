package bankAppEmulation.bankApp.controller;

import bankAppEmulation.bankApp.dto.BankAccountRequest;
import bankAppEmulation.bankApp.dto.BankAccountResponse;
import bankAppEmulation.bankApp.service.BankAccountService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/bankApp/accounts")
public class BankAccountController {

    private final BankAccountService service;

    public BankAccountController(BankAccountService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<BankAccountResponse> createAccount(@Valid @RequestBody BankAccountRequest request) {
        return new ResponseEntity<>(service.createAccount(request), HttpStatus.CREATED);
    }

    @GetMapping("/{accountNumber}")
    public ResponseEntity<BankAccountResponse> getAccount(@PathVariable String accountNumber) {
        return ResponseEntity.ok(service.getByAccountNumber(accountNumber));
    }

    @GetMapping("/validate")
    public ResponseEntity<Map<String, Boolean>> validateAccount(
            @RequestParam String accountNumber,
            @RequestParam String bankCode) {
        boolean isValid = service.validateAccount(accountNumber, bankCode);
        return ResponseEntity.ok(Map.of("valid", isValid));
    }
}
