package com.ilenkov.bank.transactionservice.entity.dto;

import java.util.Objects;

public class ChangeBalanceRequest {
    private Long id;
    private double amount;
    public ChangeBalanceRequest() {

    }
    public ChangeBalanceRequest(Long id, double amount) {
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
        ChangeBalanceRequest that = (ChangeBalanceRequest) o;
        return Double.compare(amount, that.amount) == 0 && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, amount);
    }

    @Override
    public String toString() {
        return "ChangeBalanceRequest{" +
                "id=" + id +
                ", amount=" + amount +
                '}';
    }
}
