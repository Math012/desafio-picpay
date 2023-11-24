package com.math012.Picpay.dtos;

import com.math012.Picpay.model.UserTypeModel;

public record UserDTO(String fullName, String cpf, String email, String password,Double balance ,String role) {
}
