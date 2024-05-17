package com.example.demo.domain;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
public class Account {

    @JsonProperty("아이디")
    private String id;

    @JsonProperty("비밀번호")
    private String password;

    @JsonProperty("이름")
    private String name;

    public Account() {}

    @JsonSetter("비밀번호")
    public void setPassword(String password) {
        this.password = password != null ? password.trim() : null;
    }

    @JsonSetter("이름")
    public void setName(String name) {
        this.name = name != null ? name.trim() : null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return id.equals(account.id)
                && password.equals(account.password)
                && name.equals(account.name);
    }

    @Override
    public int hashCode() { return Objects.hashCode(id + password + name); }

    @Override
    public String toString() {
        return "Account(id=" + id + ", password=" + password + ", name=" + name + ")";
    }
}
