package com.ilenkov.bank.transactionservice.entity.dto;

import java.util.Objects;

public class TransferRequest {
    private Long senderWalletId;
    private Long receiverWalletId;
    private Double amount;

    public TransferRequest() {
    }

    public TransferRequest(Long senderWalletId, Long receiverWalletId, Double amount) {
        this.senderWalletId = senderWalletId;
        this.receiverWalletId = receiverWalletId;
        this.amount = amount;
    }

    public Long getSenderWalletId() {
        return senderWalletId;
    }

    public void setSenderWalletId(Long senderWalletId) {
        this.senderWalletId = senderWalletId;
    }

    public Long getReceiverWalletId() {
        return receiverWalletId;
    }

    public void setReceiverWalletId(Long receiverWalletId) {
        this.receiverWalletId = receiverWalletId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransferRequest that = (TransferRequest) o;
        return Objects.equals(senderWalletId, that.senderWalletId) && Objects.equals(receiverWalletId, that.receiverWalletId) && Objects.equals(amount, that.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(senderWalletId, receiverWalletId, amount);
    }

    @Override
    public String toString() {
        return "TransferRequest{" +
                "senderWalletId=" + senderWalletId +
                ", receiverWalletId=" + receiverWalletId +
                ", amount=" + amount +
                '}';
    }
}
