package com.math012.Picpay.service;

import com.math012.Picpay.dtos.TransferDTO;
import com.math012.Picpay.exception.AccountNotAuthorizedException;
import com.math012.Picpay.exception.TransactionNotAuthorizedException;
import com.math012.Picpay.exception.ValueEnoughException;
import com.math012.Picpay.model.UserModel;
import com.math012.Picpay.model.UserTypeModel;
import com.math012.Picpay.repository.TransferRepository;
import com.math012.externalservice.VerifyTransferService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import java.util.Optional;
import static org.mockito.Mockito.*;


class TransferServiceTest {

    @Mock
    private TransferRepository transferRepository;

    @Mock
    private UserService userService;

    @Mock
    private VerifyTransferService verifyTransferService;

    @Autowired
    @InjectMocks
    private TransferService transferService;


    @BeforeEach
    void start(){
        MockitoAnnotations.initMocks(this);
    }


    @Test
    @DisplayName("When transfer completed successfully")
    void makeTransferSuccess() {
        UserTypeModel userCOMMON = new UserTypeModel(1L,"COMMON");
        UserTypeModel userSHOPKEEPER = new UserTypeModel(2L,"SHOPKEEPER");

        UserModel payer = new UserModel(1L,"Matheus","9999999999", "matheus@matheus.com", "1234",30.00,userCOMMON);
        UserModel payee = new UserModel(2L,"Ana","9999999988", "ana@ana.com", "1234",10.00,userSHOPKEEPER);

        when(userService.findById(1L)).thenReturn(Optional.of(payer));
        when(userService.findById(2L)).thenReturn(Optional.of(payee));
        assertNotEquals(2L, payer.getUsertype().getId());

        TransferDTO transferDTO = new TransferDTO(10.00,1L, 2L);
        assertEquals(true,payer.getBalance() >= transferDTO.value());


        when(verifyTransferService.verifyTransfer()).thenReturn(true);

        payer.setBalance(20.00);
        payee.setBalance(20.00);

        transferService.makeTransfer(transferDTO);


        verify(transferRepository, times(1)).save(any());
        verify(userService, times(1)).saveUser(payer);
        verify(userService, times(1)).saveUser(payee);

    }

    @Test
    @DisplayName("Exception when the transfer was not completed because the payer account does not have authorization")
    void makeTransferFailedByAuthorizationAccount() {

        UserTypeModel userCOMMON = new UserTypeModel(1L,"COMMON");
        UserTypeModel userSHOPKEEPER = new UserTypeModel(2L,"SHOPKEEPER");

        UserModel payee = new UserModel(1L,"Matheus","9999999999", "matheus@matheus.com", "1234",30.00,userCOMMON);
        UserModel payer = new UserModel(2L,"Ana","9999999988", "ana@ana.com", "1234",10.00,userSHOPKEEPER);


        when(userService.findById(1L)).thenReturn(Optional.of(payee));
        when(userService.findById(2L)).thenReturn(Optional.of(payer));
        assertNotEquals(1L, payer.getUsertype().getId());

        Exception err = Assertions.assertThrows(AccountNotAuthorizedException.class,()->{
            TransferDTO transferDTO = new TransferDTO(10.00,2L, 1L);
            transferService.makeTransfer(transferDTO);
        });

        Assertions.assertEquals("This account is not authorized to make transfers", err.getMessage());



    }

    @Test
    @DisplayName("When the service responsible for checking the transfer return failed")
    void makeTransferFailedByAuthorizationService() {

        UserTypeModel userCOMMON = new UserTypeModel(1L,"COMMON");
        UserTypeModel userSHOPKEEPER = new UserTypeModel(2L,"SHOPKEEPER");

        UserModel payee = new UserModel(1L,"Matheus","9999999999", "matheus@matheus.com", "1234",30.00,userCOMMON);
        UserModel payer = new UserModel(2L,"Ana","9999999988", "ana@ana.com", "1234",10.00,userSHOPKEEPER);

        when(userService.findById(1L)).thenReturn(Optional.of(payer));
        when(userService.findById(2L)).thenReturn(Optional.of(payee));

        when(verifyTransferService.verifyTransfer()).thenReturn(false);


        Exception err= Assertions.assertThrows(TransactionNotAuthorizedException.class, ()->{
            TransferDTO transferDTO = new TransferDTO(10.00,2L, 1L);
            transferService.makeTransfer(transferDTO);
        });

        Assertions.assertEquals("This transfer was not authorized by the system", err.getMessage());
    }

    @Test
    @DisplayName("When the paying user tries to make a transfer but their account balance is not enough")
    void makeTransferFailedByBalance() {
        UserTypeModel userCOMMON = new UserTypeModel(1L,"COMMON");
        UserTypeModel userSHOPKEEPER = new UserTypeModel(2L,"SHOPKEEPER");

        UserModel payer = new UserModel(1L,"Matheus","9999999999", "matheus@matheus.com", "1234",30.00,userCOMMON);
        UserModel payee = new UserModel(2L,"Ana","9999999988", "ana@ana.com", "1234",10.00,userSHOPKEEPER);

        when(userService.findById(1L)).thenReturn(Optional.of(payer));
        when(userService.findById(2L)).thenReturn(Optional.of(payee));

        assertNotEquals(2L, payer.getUsertype().getId());

        when(verifyTransferService.verifyTransfer()).thenReturn(true);

        TransferDTO transferDTO = new TransferDTO(40.00,1L, 2L);
        assertEquals(false,payer.getBalance() >= transferDTO.value());
        Exception err = Assertions.assertThrows(ValueEnoughException.class, () ->{
            transferService.makeTransfer(transferDTO);
        });

        Assertions.assertEquals("Account balance is not enough", err.getMessage());
    }
}