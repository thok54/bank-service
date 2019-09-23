package com.banking.bankservice.repository;

import com.banking.bankservice.dto.Bank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class MySqlBankRepository implements BankRepository {
    //TODO:BankAccount Controller.

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Bank> findAll() {
        return jdbcTemplate.query("select * from BANKS", new RowMapper<Bank>() {
            @Override
            public Bank mapRow(ResultSet rs, int rowNum) throws SQLException {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String address = rs.getString("address");
                return new Bank(id, name, address);
            }
        });
    }

    @Override
    public Bank find(int id) {
        try {
            return jdbcTemplate.queryForObject(String.format(
                    "select * from BANKS where id = %d", id), new RowMapper<Bank>() {
                @Override
                public Bank mapRow(ResultSet rs, int i) throws SQLException {
                    String name = rs.getString("name");
                    String address = rs.getString("address");
                    return new Bank(id, name, address);
                }
            });
        } catch (Exception e) {
            throw new EntityNotFoundException(String.format(
                    "Bank with ID = %d does not exist", id));
        }
    }

    @Override
    public List<Bank> findAllByName(String name) {
        try {
        return jdbcTemplate.query(String.format(
                "select * from BANKS where name = %s", name), new RowMapper<Bank>() {
            @Override
            public Bank mapRow(ResultSet rs, int rowNum) throws SQLException {
                int id = rs.getInt("id");
                String address = rs.getString("address");
                return new Bank(id, name, address);
            }
        });
        }catch (Exception e){
            throw new EntityNotFoundException(String.format("Bank with name = %s does not exist", name));
         }
    }


    @Override
    public void store(Bank bank) {
        String name = bank.getName();
        String address = bank.getAddress();
        jdbcTemplate.update(String.format(
                "INSERT INTO BANKS (name, address) VALUES (\"%s\",\"%s\")", name, address));
    }

    @Override
    public void update(int id, Bank bank) {
        String name = bank.getName();
        String address = bank.getAddress();
        jdbcTemplate.update(String.format(
                "UPDATE BANKS SET name = \"%s\", address = \"%s\" WHERE id = %d", name, address, id));
    }

    @Override
    public void delete(int id) {//Does not delete primary key, so deleted id become unusable
        jdbcTemplate.update(String.format("DELETE FROM BANKS WHERE id = " + id));
    }
}
