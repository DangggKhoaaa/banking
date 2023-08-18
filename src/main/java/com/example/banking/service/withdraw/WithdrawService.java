package com.example.banking.service.withdraw;

import com.example.banking.model.Customers;
import com.example.banking.model.Deposits;
import com.example.banking.model.Withdraws;
import com.example.banking.repository.CustomerRepository;
import com.example.banking.repository.WithdrawRepository;
import com.example.banking.service.customer.WithdrawSaveRequest;
import com.example.banking.util.AppUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@AllArgsConstructor
@Transactional
public class WithdrawService {

    private final WithdrawRepository withdrawRepository;

    private final CustomerRepository customerRepository;

    public Customers decrementAmount(WithdrawSaveRequest request, Long id) {
        Withdraws withdraw = AppUtils.mapper.map(request, Withdraws.class);
        Customers customer = customerRepository.findById(id).get();

        withdraw.setCustomerWithdraw(customer);
        withdraw.setDate(LocalDate.now());
        withdrawRepository.save(withdraw);

        customer.setBalance(customer.getBalance().subtract(withdraw.getWithdraw()));
        customerRepository.save(customer);

        return customer;
    }
}
