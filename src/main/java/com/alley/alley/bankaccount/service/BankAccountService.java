package com.alley.alley.bankaccount.service;

import com.alley.alley.bankaccount.dto.BankAccountRequest;
import com.alley.alley.bankaccount.dto.BankAccountResponse;
import com.alley.alley.bankaccount.dto.BankAccountUpdateRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BankAccountService {
    BankAccountResponse createBankAccount(BankAccountRequest request);
    BankAccountResponse updateBankAccount(String id, BankAccountUpdateRequest request);
    BankAccountResponse getBankAccountById(String id);
    Page<BankAccountResponse> getAllBankAccounts(Pageable pageable);
    void deleteBankAccount(String id);
}
