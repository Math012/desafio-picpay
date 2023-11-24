package com.math012.Picpay.service;

import com.math012.Picpay.dtos.TransferDTO;
import com.math012.Picpay.exception.AccountNotAuthorizedException;
import com.math012.Picpay.exception.TransactionNotAuthorizedException;
import com.math012.Picpay.exception.ValueEnoughException;
import com.math012.Picpay.model.TransferModel;
import com.math012.Picpay.model.UserModel;
import com.math012.Picpay.repository.TransferRepository;
import com.math012.externalservice.VerifyTransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;


@Service
public class TransferService {



    @Autowired
    private TransferRepository repository;

    @Autowired
    private UserService userService;

    @Autowired
    private VerifyTransferService verifyTransfer;


    public TransferDTO makeTransfer(TransferDTO transferDTO){

        UserModel payer = userService.findById(transferDTO.payer()).get();
        UserModel payee = userService.findById(transferDTO.payee()).get();


        if (payer.getUsertype().getId() == 2L) throw new AccountNotAuthorizedException("This account is not authorized to make transfers");
        if (payer.getBalance() - transferDTO.value() < 0) throw new ValueEnoughException("Account balance is not enough");
        if (this.verifyTransfer.verifyTransfer() == false) throw new TransactionNotAuthorizedException("This transfer was not authorized by the system");

        payer.setBalance(payer.getBalance() - transferDTO.value());
        payee.setBalance(payee.getBalance() + transferDTO.value());

        userService.saveUser(payer);
        userService.saveUser(payee);

        TransferModel transferModel = new TransferModel();
        transferModel.setUserSentId(transferDTO.payer());
        transferModel.setUserReceivedId(transferDTO.payee());
        transferModel.setValue(transferDTO.value());

        repository.save(transferModel);

        return transferDTO;
    }


}
