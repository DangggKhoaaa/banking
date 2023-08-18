package com.example.banking.service.transfer;

import com.example.banking.model.Customers;
import com.example.banking.model.Transfers;
import com.example.banking.repository.CustomerRepository;
import com.example.banking.repository.TransferRepository;
import com.example.banking.service.customer.TransferSaveRequest;
import com.example.banking.util.AppUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
@AllArgsConstructor
@Transactional
public class TransferService {

    private final TransferRepository transferRepository;

    private final CustomerRepository customerRepository;

    public Customers transfer(TransferSaveRequest request) {
        Transfers transfer = AppUtils.mapper.map(request, Transfers.class);

        Customers sender = customerRepository.findById(Long.valueOf(request.getSenderId())).orElseThrow(() -> new RuntimeException("Không tìm thấy người gửi"));
        Customers recipient = customerRepository.findById(Long.valueOf(request.getRecipientId())).orElseThrow(() -> new RuntimeException("Không tìm thấy người nhận"));

        transfer.setSender(sender);
        transfer.setRecipient(recipient);
        transfer.setDate(LocalDate.now());
        transferRepository.save(transfer);

        sender.setBalance(sender.getBalance().subtract(transfer.getTransactionAmount()));
        recipient.setBalance(recipient.getBalance().add(transfer.getTransactionAmount()));
        customerRepository.save(sender);
        customerRepository.save(recipient);

        return sender;
    }
}
