package com.example.banking.service.customer;

import com.example.banking.model.Customers;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class TransferSaveRequest {

    private String transactionAmount;

    private String senderId;

    private String recipientId;
}
