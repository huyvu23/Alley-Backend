package com.alley.alley.bankaccount.entity;

import org.hibernate.annotations.UuidGenerator;

import com.alley.alley.user.entity.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "bank_accounts")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BankAccount {

    @Id
    @GeneratedValue // Hibernate sẽ tự hiểu khi dùng với @UuidGenerator
    @UuidGenerator // Tự động tạo UUID phía Java trước khi insert
    @Column(name = "id", updatable = false, nullable = false)
    private String id;

    @Column(name = "account_number", nullable = false, unique = true)
    private String accountNumber;

    @Column(name = "account_name", nullable = false)
    private String accountName;

    @Column(name = "bank_name", nullable = false)
    private String bankName;

    @Column(name = "bank_code", nullable = false)
    private String bankCode;

    @Column(name = "bank_branch", nullable = true)
    private String bankBranch;

    @Column(name = "bank_logo", nullable = false)
    private String bankLogo;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

}