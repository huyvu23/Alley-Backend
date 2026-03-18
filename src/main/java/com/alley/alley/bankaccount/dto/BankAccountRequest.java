package com.alley.alley.bankaccount.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BankAccountRequest {

    @NotBlank(message = "Account number is required")
    private String accountNumber;

    @NotBlank(message = "Account name is required")
    private String accountName;

    @NotBlank(message = "Bank name is required")
    private String bankName;

    @NotBlank(message = "Bank code is required")
    private String bankCode;

    private String bankBranch;

    @NotBlank(message = "Bank logo is required")
    private String bankLogo;

    @NotBlank(message = "User ID is required")
    private String userId;

}
