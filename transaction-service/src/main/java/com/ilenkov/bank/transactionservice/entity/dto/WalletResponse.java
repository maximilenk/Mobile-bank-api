package com.ilenkov.bank.transactionservice.entity.dto;

import java.util.Objects;

public class WalletResponse {
    private Long id;
    private double balance;
    private Long ownerId;

    public WalletResponse() {
    }

    public WalletResponse(Long id, double balance, Long ownerId) {
        this.id = id;
        this.balance = balance;
        this.ownerId = ownerId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WalletResponse that = (WalletResponse) o;
        return Double.compare(balance, that.balance) == 0 && Objects.equals(id, that.id) && Objects.equals(ownerId, that.ownerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, balance, ownerId);
    }

    @Override
    public String toString() {
        return "WalletResponce{" +
                "id=" + id +
                ", balance=" + balance +
                ", ownerId=" + ownerId +
                '}';
    }
}
