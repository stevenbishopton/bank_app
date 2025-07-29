package bankAppEmulation.bankApp.dto;

import lombok.Data;

@Data
public class BankAccountResponse {
    private String bankCode;
    private String accountNumber;
    private String accountName;
}
