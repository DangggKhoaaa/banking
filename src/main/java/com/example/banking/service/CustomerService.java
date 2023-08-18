package com.example.banking.service;

import com.example.banking.model.Customers;
import com.example.banking.repository.CustomerRepository;
import com.example.banking.service.customer.CustomerSaveRequest;
import com.example.banking.util.AppUtils;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@AllArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;


    public List<Customers> findAll() {
        return customerRepository.findAll();
    }

    public Page<Customers> findAll(Pageable pageable) {
        return customerRepository.findAll(pageable);
    }

    public Customers findById(Long id) {
        return customerRepository.findById(id).get();
    }
    public void create(CustomerSaveRequest request) {
        Customers newCustomer = AppUtils.mapper.map(request, Customers.class);
        customerRepository.save(newCustomer);
    }

    public Customers update(CustomerSaveRequest request, Long id) {
        Customers newCustomer = AppUtils.mapper.map(request, Customers.class);
        Customers oldCustomer = findById(id);
        newCustomer.setId(id);
        newCustomer.setBalance(oldCustomer.getBalance());
        customerRepository.save(newCustomer);
        return newCustomer;
    }

    public void delete(Long id) {
        customerRepository.deleteById(id);
    }
}
