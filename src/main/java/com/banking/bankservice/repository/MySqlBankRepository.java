package com.banking.bankservice.repository;

import com.banking.bankservice.dto.Bank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class MySqlBankRepository implements BankRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private Bank mapRow(ResultSet rs, int rowNum) throws SQLException {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        String address = rs.getString("address");
        return new Bank(id, name, address);
    }

    @Override
    public List<Bank> findAll() {
        return jdbcTemplate.query("select * from BANKS", this::mapRow);
    }

    @Override
    public Bank find(int id) {
        try {
            return jdbcTemplate.queryForObject(String.format(
                    "select * from BANKS where id = %d", id), this::mapRow);
        } catch (Exception e) {
            throw new EntityNotFoundException(String.format(
                    "Bank with ID = %d does not exist", id));
        }
    }

    @Override
    public List<Bank> findAllByName(String name) {
        try {
            return jdbcTemplate.query(String.format(
                    "select * from BANKS where name = %s", name), this::mapRow);
        } catch (Exception e) {
            throw new EntityNotFoundException(
                    String.format("Bank with name = %s does not exist", name));
        }
    }


    @Override
    public void store(Bank bank) {
        String name = bank.getName();
        String address = bank.getAddress();
        jdbcTemplate.update(
                "INSERT INTO BANKS (name, address) VALUES (?, ?)", name, address);
    }

    @Override
    public void update(int id, Bank bank) {
        String name = bank.getName();
        String address = bank.getAddress();
        jdbcTemplate.update(
                "UPDATE BANKS SET name = ?, address = ? WHERE id = ?", name, address, id);
    }

    @Override
    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM BANKS WHERE id = " + id);
    }
}
