package com.example.banking.repository;

import com.example.banking.model.Transfers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransferRepository extends JpaRepository<Transfers, Long> {
}
