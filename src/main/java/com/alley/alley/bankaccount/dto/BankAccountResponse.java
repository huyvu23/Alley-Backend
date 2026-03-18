package com.alley.alley.bankaccount.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BankAccountResponse {

    private String id;
    private String accountNumber;
    private String accountName;
    private String bankName;
    private String bankCode;
    private String bankBranch;
    private String bankLogo;
    private UserResponse user;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UserResponse {
        private String id;
        private String firstName;
        private String lastName;
        private String email;
    }

}
