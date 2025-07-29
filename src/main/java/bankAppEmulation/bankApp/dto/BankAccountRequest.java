package bankAppEmulation.bankApp.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class BankAccountRequest {
    @NotNull
    @Size(min = 3, max = 3)
    private String bankCode;

    @NotNull
    @Size(min = 3)
    private String accountName;
}
