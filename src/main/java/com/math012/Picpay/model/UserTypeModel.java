package com.math012.Picpay.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "user_type")
public class UserTypeModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "user_type")
    private String userType;

    @JsonIgnore
    @OneToMany(mappedBy = "usertype")
    private List<UserModel> userModel;

    public UserTypeModel(){}

    public UserTypeModel(Long id,String userType){
        this.id = id;
        this.userType = userType;
    }

    public UserTypeModel(String userType, List<UserModel> userModel) {
        this.userType = userType;
        this.userModel = userModel;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public List<UserModel> getUserModel() {
        return userModel;
    }

    public void setUserModel(List<UserModel> userModel) {
        this.userModel = userModel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserTypeModel that = (UserTypeModel) o;
        return id == that.id && Objects.equals(userType, that.userType) && Objects.equals(userModel, that.userModel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userType, userModel);
    }

    @Override
    public String toString() {
        return "UserTypeModel{" +
                "id=" + id +
                ", userType='" + userType + '\'' +
                ", userModel=" + userModel +
                '}';
    }
}
