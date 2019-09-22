package com.banking.bankservice.repository;

import com.banking.bankservice.dto.Bank;
import com.banking.bankservice.util.DataBaseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MySqlBankRepository implements BankRepository {

    @Autowired
    private DataBaseUtil dataBaseUtil;//TODO: Replace DatabaseUtil(delete when finished) for Jdbc. BankAccount Controller.

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
        Connection con = dataBaseUtil.startConnection();
        try {
            PreparedStatement pstmt = con.prepareStatement(String.format("select * from BANKS where id = %d", id));
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                String name = rs.getString("name");
                String address = rs.getString("address");
                return new Bank(id, name, address);
            }
            throw new EntityNotFoundException(String.format("Bank with ID = %d does not exist", id));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            dataBaseUtil.closeConections(con);
        }
        return null;
    }

    @Override
    public List<Bank> findAllByName(String name) {
        List<Bank> banks = new ArrayList();
        Connection con = dataBaseUtil.startConnection();
        try {
            PreparedStatement pstmt = con.prepareStatement(String.format("select * from BANKS where name = %s", name));
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Integer id = rs.getInt("id");
                String address = rs.getString("address");
                Bank bank = new Bank(id, name, address);
                banks.add(bank);
            }
            if (banks.isEmpty()) {
                throw new EntityNotFoundException(String.format("Bank with name = %s does not exist", name));
            }
            return banks;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            dataBaseUtil.closeConections(con);
        }
        return banks;
    }


    @Override
    public void store(Bank bank) {
        Connection con = dataBaseUtil.startConnection();
        try {
            String name = bank.getName();
            String address = bank.getAddress();

            String query = String.format("INSERT INTO BANKS (name, address) VALUES (\"%s\",\"%s\")", name, address);
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dataBaseUtil.closeConections(con);
        }
    }

    @Override
    public void update(int id, Bank bank) {
        Connection con = dataBaseUtil.startConnection();
        try {
            String name = bank.getName();
            String address = bank.getAddress();
            String query = String.format("UPDATE BANKS SET name = \"%s\", address = \"%s\" WHERE id = %d", name, address, id);
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dataBaseUtil.closeConections(con);
        }
    }

    @Override
    public void delete(int id) {
        Connection con = dataBaseUtil.startConnection();
        try {
            PreparedStatement pstmt = con.prepareStatement("DELETE FROM BANKS WHERE id = " + id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dataBaseUtil.closeConections(con);
        }

    }
}
