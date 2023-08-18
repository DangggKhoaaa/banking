package com.example.banking.repository;

import com.example.banking.model.Deposits;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepositRepository extends JpaRepository<Deposits, Long> {
}
