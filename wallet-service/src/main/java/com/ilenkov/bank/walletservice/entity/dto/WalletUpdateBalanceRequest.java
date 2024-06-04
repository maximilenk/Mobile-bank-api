package com.ilenkov.bank.walletservice.entity.dto;

import java.util.Objects;

public class WalletUpdateBalanceRequest {
    private Long id;
    private double amount;

    public WalletUpdateBalanceRequest() {}

    public WalletUpdateBalanceRequest(Long id, double amount) {
        this.id = id;
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WalletUpdateBalanceRequest that = (WalletUpdateBalanceRequest) o;
        return Double.compare(amount, that.amount) == 0 && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, amount);
    }

    @Override
    public String toString() {
        return "WalletUpdateBalanceRequest{" +
                "id=" + id +
                ", amount=" + amount +
                '}';
    }
}
