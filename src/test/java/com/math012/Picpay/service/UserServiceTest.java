package com.math012.Picpay.service;

import com.math012.Picpay.dtos.UserDTO;
import com.math012.Picpay.exception.UserLoginException;
import com.math012.Picpay.model.UserModel;
import com.math012.Picpay.model.UserTypeModel;
import com.math012.Picpay.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.opentest4j.AssertionFailedError;
import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import java.util.Optional;
import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserTypeService userTypeService;

    @Autowired
    @InjectMocks
    private UserService service;

    @BeforeEach
    void start(){
        MockitoAnnotations.initMocks(this);
    }


    @Test
    @DisplayName("When the account is created successfully")
    void createUserSuccess() {
        UserTypeModel userCOMMON = new UserTypeModel(1L,"COMMON");
        UserTypeModel userSHOPKEEPER = new UserTypeModel(2L,"SHOPKEEPER");

        UserDTO userDTO = new UserDTO("Matheus", "192839201932", "math@picpay.com","12345", 30.00, userCOMMON.getUserType());
        when(userTypeService.findByName(userCOMMON.getUserType())).thenReturn(userCOMMON);

        service.createUser(userDTO);

        UserModel user = new UserModel(userDTO.fullName(),userDTO.cpf(), userDTO.email(), userDTO.password(), userDTO.balance(), userCOMMON);
        verify(userRepository, times(1)).save(user);
    }
}