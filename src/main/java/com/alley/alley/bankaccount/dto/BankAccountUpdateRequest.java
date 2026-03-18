package com.alley.alley.bankaccount.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BankAccountUpdateRequest {

    private String accountNumber;
    private String accountName;
    private String bankName;
    private String bankCode;
    private String bankBranch;
    private String bankLogo;
    private String userId;

}
