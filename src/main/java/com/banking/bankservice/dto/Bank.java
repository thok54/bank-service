package com.banking.bankservice.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Bank {

    private int id;
    private String name;
    private String address;
}
