package com.example.banking.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Customers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fullName;
    private String phone;
    private String email;
    private String address;
    private BigDecimal balance;

    @OneToMany(mappedBy = "customerDeposit")
    @JsonIgnore
    private List<Deposits> deposits;

    @OneToMany(mappedBy = "customerWithdraw")
    @JsonIgnore
    private List<Withdraws> withdraws;

    @OneToMany(mappedBy = "sender")
    @JsonIgnore
    private List<Transfers> sentTransfers;

    @OneToMany(mappedBy = "recipient")
    @JsonIgnore
    private List<Transfers> receivedTransfers;
}
