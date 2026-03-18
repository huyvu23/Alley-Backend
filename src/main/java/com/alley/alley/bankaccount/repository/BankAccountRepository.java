package com.alley.alley.bankaccount.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.alley.alley.bankaccount.entity.BankAccount;

import java.util.Optional;

public interface BankAccountRepository extends JpaRepository<BankAccount, String> {
    Optional<BankAccount> findByAccountNumber(String accountNumber);
}
