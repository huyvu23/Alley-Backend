package com.alley.alley.bankaccount.service;

import com.alley.alley.bankaccount.dto.BankAccountRequest;
import com.alley.alley.bankaccount.dto.BankAccountResponse;
import com.alley.alley.bankaccount.dto.BankAccountUpdateRequest;
import com.alley.alley.bankaccount.entity.BankAccount;
import com.alley.alley.bankaccount.repository.BankAccountRepository;
import com.alley.alley.user.entity.User;
import com.alley.alley.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BankAccountServiceImpl implements BankAccountService {

    private final BankAccountRepository bankAccountRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public BankAccountResponse createBankAccount(BankAccountRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found with id: " + request.getUserId()));

        BankAccount bankAccount = BankAccount.builder()
                .accountNumber(request.getAccountNumber())
                .accountName(request.getAccountName())
                .bankName(request.getBankName())
                .bankCode(request.getBankCode())
                .bankBranch(request.getBankBranch())
                .bankLogo(request.getBankLogo())
                .user(user)
                .build();

        BankAccount savedAccount = bankAccountRepository.save(bankAccount);
        return mapToResponse(savedAccount);
    }

    @Override
    @Transactional
    public BankAccountResponse updateBankAccount(String id, BankAccountUpdateRequest request) {
        BankAccount bankAccount = bankAccountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("BankAccount not found with id: " + id));

        if (request.getAccountNumber() != null) bankAccount.setAccountNumber(request.getAccountNumber());
        if (request.getAccountName() != null) bankAccount.setAccountName(request.getAccountName());
        if (request.getBankName() != null) bankAccount.setBankName(request.getBankName());
        if (request.getBankCode() != null) bankAccount.setBankCode(request.getBankCode());
        if (request.getBankBranch() != null) bankAccount.setBankBranch(request.getBankBranch());
        if (request.getBankLogo() != null) bankAccount.setBankLogo(request.getBankLogo());
        
        if (request.getUserId() != null) {
            User user = userRepository.findById(request.getUserId())
                    .orElseThrow(() -> new RuntimeException("User not found with id: " + request.getUserId()));
            bankAccount.setUser(user);
        }

        BankAccount updatedAccount = bankAccountRepository.save(bankAccount);
        return mapToResponse(updatedAccount);
    }

    @Override
    @Transactional(readOnly = true)
    public BankAccountResponse getBankAccountById(String id) {
        BankAccount bankAccount = bankAccountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("BankAccount not found with id: " + id));
        return mapToResponse(bankAccount);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<BankAccountResponse> getAllBankAccounts(Pageable pageable) {
        return bankAccountRepository.findAll(pageable).map(this::mapToResponse);
    }

    @Override
    @Transactional
    public void deleteBankAccount(String id) {
        if (!bankAccountRepository.existsById(id)) {
            throw new RuntimeException("BankAccount not found with id: " + id);
        }
        bankAccountRepository.deleteById(id);
    }

    private BankAccountResponse mapToResponse(BankAccount bankAccount) {
        BankAccountResponse.UserResponse userResponse = null;
        if (bankAccount.getUser() != null) {
            userResponse = BankAccountResponse.UserResponse.builder()
                    .id(bankAccount.getUser().getId())
                    .firstName(bankAccount.getUser().getFirstName())
                    .lastName(bankAccount.getUser().getLastName())
                    .email(bankAccount.getUser().getEmail())
                    .build();
        }

        return BankAccountResponse.builder()
                .id(bankAccount.getId())
                .accountNumber(bankAccount.getAccountNumber())
                .accountName(bankAccount.getAccountName())
                .bankName(bankAccount.getBankName())
                .bankCode(bankAccount.getBankCode())
                .bankBranch(bankAccount.getBankBranch())
                .bankLogo(bankAccount.getBankLogo())
                .user(userResponse)
                .build();
    }
}
