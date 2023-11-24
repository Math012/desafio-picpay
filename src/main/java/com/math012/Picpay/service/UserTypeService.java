package com.math012.Picpay.service;

import com.math012.Picpay.model.UserTypeModel;
import com.math012.Picpay.repository.UserTypeRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserTypeService {

    @Autowired
    private UserTypeRepository repository;

    public List<UserTypeModel> findAll(){
        return repository.findAll();
    }

    public UserTypeModel findByName(String name){
        UserTypeModel userTypeModel = repository.findByNameRole(name);
        return userTypeModel;

    }

    public UserTypeModel createUserType(String userType){
        UserTypeModel userTypeModel = new UserTypeModel();
        userTypeModel.setUserType(userType);
        return repository.save(userTypeModel);
    }


}
