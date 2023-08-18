package com.example.banking.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Transfers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal transactionAmount;

    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "sender_id")
    private Customers sender;

    @ManyToOne
    @JoinColumn(name = "recipient_id")
    private Customers recipient;
}
