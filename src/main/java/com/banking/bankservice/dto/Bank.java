package com.banking.bankservice.dto;

import java.util.Objects;
import java.util.StringJoiner;

public class Bank {

    private int id;
    private String name;
    private String address;

    public Bank() {
    }

    public Bank(int id) {
        this.id = id;
    }

    public Bank(int id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public String getAddress() {
        return address;
    }


    public void setAddress(String address) {
        this.address = address;
    }


    @Override
    public String toString() {
        return new StringJoiner(", ", Bank.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("name='" + name + "'")
                .add("address='" + address + "'")
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bank bank = (Bank) o;
        return id == bank.id &&
                Objects.equals(name, bank.name) &&
                Objects.equals(address, bank.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, address);
    }
}
