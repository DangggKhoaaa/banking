package com.example.banking.service.deposit;

import com.example.banking.model.Customers;
import com.example.banking.model.Deposits;
import com.example.banking.repository.CustomerRepository;
import com.example.banking.repository.DepositRepository;
import com.example.banking.service.customer.DepositSaveRequest;
import com.example.banking.util.AppUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@AllArgsConstructor
@Transactional
public class DepositService {

    private final DepositRepository depositRepository;

    private final CustomerRepository customerRepository;

    public Customers add(DepositSaveRequest request, Long id) {
        Deposits deposit = AppUtils.mapper.map(request, Deposits.class);
        Customers customer = customerRepository.findById(id).get();

        deposit.setCustomerDeposit(customer);
        deposit.setDate(LocalDate.now());
        depositRepository.save(deposit);

        customer.setBalance(customer.getBalance().add(deposit.getDeposit()));
        customerRepository.save(customer);

        return customer;
    }
}
