package com.banking.bankservice.repository.mapper;

import com.banking.bankservice.dto.Bank;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BankRowMapperTest {

    @Mock
    private ResultSet resultSet;

    private BankRowMapper mapper = new BankRowMapper();

    @Test
    public void shouldMapRowUsingBank() throws SQLException {
        when(resultSet.getInt("id")).thenReturn(1);
        when(resultSet.getString("name")).thenReturn("a name");
        when(resultSet.getString("address")).thenReturn("an address");

        Bank expected = mapper.mapRow(resultSet, 0);

        assertNotNull(expected);
        assertEquals(1, expected.getId());
        assertEquals("a name", expected.getName());
        assertEquals("an address", expected.getAddress());
    }
}
