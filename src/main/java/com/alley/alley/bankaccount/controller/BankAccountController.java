package com.alley.alley.bankaccount.controller;

import com.alley.alley.bankaccount.dto.BankAccountRequest;
import com.alley.alley.bankaccount.dto.BankAccountResponse;
import com.alley.alley.bankaccount.dto.BankAccountUpdateRequest;
import com.alley.alley.bankaccount.service.BankAccountService;
import com.alley.alley.common.dtos.ApiResponse;
import com.alley.alley.common.dtos.MetaResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bank-accounts")
@RequiredArgsConstructor
public class BankAccountController {

    private final BankAccountService bankAccountService;

    @PostMapping
    public ApiResponse<BankAccountResponse> createBankAccount(@Valid @RequestBody BankAccountRequest request) {
        return ApiResponse.success(bankAccountService.createBankAccount(request));
    }

    @PutMapping("/{id}")
    public ApiResponse<BankAccountResponse> updateBankAccount(@PathVariable String id, @RequestBody BankAccountUpdateRequest request) {
        return ApiResponse.success(bankAccountService.updateBankAccount(id, request));
    }

    @GetMapping("/{id}")
    public ApiResponse<BankAccountResponse> getBankAccountById(@PathVariable String id) {
        return ApiResponse.success(bankAccountService.getBankAccountById(id));
    }

    @GetMapping
    public ApiResponse<List<BankAccountResponse>> getAllBankAccounts(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction) {
        Sort sort = direction.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page - 1, pageSize, sort);
        Page<BankAccountResponse> accountPage = bankAccountService.getAllBankAccounts(pageable);

        return ApiResponse.success(accountPage.getContent(), MetaResponse.fromPage(accountPage));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<String> deleteBankAccount(@PathVariable String id) {
        bankAccountService.deleteBankAccount(id);
        return ApiResponse.success("Bank account deleted successfully");
    }
}
