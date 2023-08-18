package com.example.banking.api;

import com.example.banking.model.Customers;
import com.example.banking.service.CustomerService;
import com.example.banking.service.customer.CustomerSaveRequest;
import com.example.banking.service.customer.DepositSaveRequest;
import com.example.banking.service.customer.TransferSaveRequest;
import com.example.banking.service.customer.WithdrawSaveRequest;
import com.example.banking.service.deposit.DepositService;
import com.example.banking.service.transfer.TransferService;
import com.example.banking.service.withdraw.WithdrawService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
@AllArgsConstructor
public class BankingRestController {
    private final CustomerService customerService;

    private final DepositService depositService;

    private final WithdrawService withdrawService;

    private final TransferService transferService;

    @GetMapping
    public Page<Customers> findAll(@PageableDefault()Pageable pageable) {
        return customerService.findAll(pageable);
    }

    @GetMapping("/all")
    public List<Customers> findAll() {
        return customerService.findAll();
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody CustomerSaveRequest request) {
        customerService.create(request);
        return ResponseEntity.ok(request);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getById( @PathVariable Long id) {
        Customers customer = customerService.findById(id);
        return ResponseEntity.ok(customer);
    }
    @PatchMapping("{id}")
    public ResponseEntity<?> update(@RequestBody CustomerSaveRequest request, @PathVariable Long id) {
        Customers customer = customerService.update(request, id);
        return ResponseEntity.ok(customer);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        customerService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/deposit/{id}")
    public ResponseEntity<?> recharge(@RequestBody DepositSaveRequest request, @PathVariable Long id) {
        Customers customer = depositService.add(request, id);
        return ResponseEntity.ok(customer);
    }

    @PostMapping("/withdraw/{id}")
    public ResponseEntity<?> decrementAmount(@RequestBody WithdrawSaveRequest request, @PathVariable Long id) {
        Customers customers = withdrawService.decrementAmount(request, id);
        return ResponseEntity.ok(customers);
    }

    @PostMapping("/transfer")
    public ResponseEntity<?> transactionAmount(@RequestBody TransferSaveRequest request) {
        Customers customer = transferService.transfer(request);
        return ResponseEntity.ok(customer);
    }
}
