package com.math012.Picpay.service;

import com.math012.Picpay.dtos.UserDTO;
import com.math012.Picpay.exception.UserLoginException;
import com.math012.Picpay.model.UserModel;
import com.math012.Picpay.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private UserTypeService userTypeService;

    public List<UserModel> findAll(){
        return repository.findAll();
    }

    public UserModel createUser(UserDTO userDTO){
        try{
            var userType =  userTypeService.findByName(userDTO.role());
            UserModel userModel = new UserModel();
            userModel.setFullName(userDTO.fullName());
            userModel.setCpf(userDTO.cpf());
            userModel.setEmail(userDTO.email());
            userModel.setPassword(userDTO.password());
            userModel.setBalance(userDTO.balance());
            userModel.setUsertype(userType);
            repository.save(userModel);
            return userModel;
        }catch (Exception e){
            throw new UserLoginException("Failed to create a new account, please try again!");
        }

    }

    public Optional<UserModel> findById(Long id){
        return repository.findById(id);
    }

    public void saveUser(UserModel user){
        repository.save(user);
    }
}
