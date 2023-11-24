package com.math012.Picpay.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "user_transfer")
public class TransferModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "sent_id")
    private long UserSentId;

    @Column(name = "received_id")
    private long UserReceivedId;

    @Column(name = "transfer_value")
    private Double value;

    public TransferModel() {}

    public TransferModel(long userSentId, long userReceivedId, Double value) {
        UserSentId = userSentId;
        UserReceivedId = userReceivedId;
        this.value = value;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserSentId() {
        return UserSentId;
    }

    public void setUserSentId(long userSentId) {
        UserSentId = userSentId;
    }

    public long getUserReceivedId() {
        return UserReceivedId;
    }

    public void setUserReceivedId(long userReceivedId) {
        UserReceivedId = userReceivedId;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransferModel that = (TransferModel) o;
        return id == that.id && UserSentId == that.UserSentId && UserReceivedId == that.UserReceivedId && Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, UserSentId, UserReceivedId, value);
    }
}
