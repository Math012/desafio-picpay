package com.math012.Picpay.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "users")
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "cpf", unique = true)
    private String cpf;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "balance")
    private Double balance;

    @ManyToOne
    @JoinColumn(name = "user_type")
    private UserTypeModel usertype;

    public UserModel(){}

    public UserModel(String fullName, String cpf, String email, String password,Double balance ,UserTypeModel usertype) {
        this.fullName = fullName;
        this.cpf = cpf;
        this.email = email;
        this.password = password;
        this.balance = balance;
        this.usertype = usertype;
    }

    public UserModel(Long id ,String fullName, String cpf, String email, String password,Double balance ,UserTypeModel usertype) {
        this.id = id;
        this.fullName = fullName;
        this.cpf = cpf;
        this.email = email;
        this.password = password;
        this.balance = balance;
        this.usertype = usertype;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserTypeModel getUsertype() {
        return usertype;
    }

    public void setUsertype(UserTypeModel usertype) {
        this.usertype = usertype;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserModel userModel = (UserModel) o;
        return id == userModel.id && Objects.equals(fullName, userModel.fullName) && Objects.equals(cpf, userModel.cpf) && Objects.equals(email, userModel.email) && Objects.equals(password, userModel.password) && Objects.equals(balance, userModel.balance) && Objects.equals(usertype, userModel.usertype);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fullName, cpf, email, password, balance, usertype);
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", cpf='" + cpf + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", balance=" + balance +
                ", usertype=" + usertype +
                '}';
    }
}
