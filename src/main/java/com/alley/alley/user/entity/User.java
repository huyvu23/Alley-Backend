package com.alley.alley.user.entity;

import java.util.List;

import org.hibernate.annotations.UuidGenerator;

import com.alley.alley.bankaccount.entity.BankAccount;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue // Hibernate sẽ tự hiểu khi dùng với @UuidGenerator
    @UuidGenerator // Tự động tạo UUID phía Java trước khi insert
    @Column(name = "id", updatable = false, nullable = false)
    private String id;

    @Column(name = "last_name", nullable = false, length = 50)
    private String lastName;

    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;

    @Column(name = "phone_number", nullable = false, unique = true, length = 11)
    private String phoneNumber;

    @Column(name = "password", nullable = false, length = 255)
    private String password;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "role", nullable = false)
    private String role;

    @OneToMany(mappedBy = "user")
    private List<BankAccount> bankAccounts;
}
