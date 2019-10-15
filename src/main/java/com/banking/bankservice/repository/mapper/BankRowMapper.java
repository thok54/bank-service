package com.banking.bankservice.repository.mapper;


import com.banking.bankservice.dto.Bank;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BankRowMapper implements RowMapper<Bank> {

    @Override
    public Bank mapRow(ResultSet rs, int rowNum) throws SQLException {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        String address = rs.getString("address");

        Bank bank = new Bank();
        bank.setId(id);
        bank.setName(name);
        bank.setAddress(address);

        return bank;
    }
}
