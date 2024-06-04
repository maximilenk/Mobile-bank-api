package com.ilenkov.bank.identityservice.entity.dto;

import java.util.Objects;

public class UserInfoRequest {
    private String email;
    private String name;
    private Long user_id;

    public UserInfoRequest() {
    }

    public UserInfoRequest(String email, String name, Long user_id) {
        this.email = email;
        this.name = name;
        this.user_id = user_id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserInfoRequest that = (UserInfoRequest) o;
        return Objects.equals(email, that.email) && Objects.equals(name, that.name) && Objects.equals(user_id, that.user_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, name, user_id);
    }

    @Override
    public String toString() {
        return "CreateUserInfoRequest{" +
                "email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", user_id=" + user_id +
                '}';
    }
}
