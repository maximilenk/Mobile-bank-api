package com.ilenkov.bank.walletservice.entity.dto;

import java.util.Objects;

public class CreateWalletRequest {
    private Long owner_id;

    public CreateWalletRequest() {
    }

    public CreateWalletRequest(Long owner_id) {
        this.owner_id = owner_id;
    }

    public Long getOwner_id() {
        return owner_id;
    }
    public void setOwner_id(Long owner_id) {
        this.owner_id = owner_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreateWalletRequest that = (CreateWalletRequest) o;
        return Objects.equals(owner_id, that.owner_id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(owner_id);
    }

    @Override
    public String toString() {
        return "CreateWalletResponse{" +
                "owner_id=" + owner_id +
                '}';
    }
}
