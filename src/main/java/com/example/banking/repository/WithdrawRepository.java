package com.example.banking.repository;

import com.example.banking.model.Withdraws;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WithdrawRepository extends JpaRepository<Withdraws, Long> {
}
