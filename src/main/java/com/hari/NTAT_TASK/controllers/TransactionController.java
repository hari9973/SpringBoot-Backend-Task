package com.hari.NTAT_TASK.controllers;

import com.hari.NTAT_TASK.models.TransactionRequest;
import com.hari.NTAT_TASK.models.TransactionResponse;
import com.hari.NTAT_TASK.models.Transactions;
import com.hari.NTAT_TASK.models.Users;
import com.hari.NTAT_TASK.repository.TransactionsRepository;
import com.hari.NTAT_TASK.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.*;

@RestController
public class TransactionController {

    @Autowired
    private UserRepository repository;

    @Autowired
    private TransactionsRepository transactionsRepository;

    @GetMapping("/transactions")
    public List<Transactions> GetTransactions(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        List<Transactions> sen = transactionsRepository.findBySender(auth.getName());
        List<Transactions> rec = transactionsRepository.findByReceiver(auth.getName());
        sen.addAll(rec);
        rec.clear();
        Collections.sort(sen);
        Collections.reverse(sen);
        return sen;
    }

    @PostMapping("/transfer")
    public TransactionResponse MakeTransaction(@RequestBody TransactionRequest transactionRequest)
            throws Exception{
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ssZ");
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            Optional<Users> sender = repository.findByusername(transactionRequest.getSender());
            Optional<Users> receiver = repository.findByusername(transactionRequest.getReceiver());
            if(sender.isEmpty())
                throw new InvalidTransactionException("cannot find sender "+transactionRequest.getSender());
            if(receiver.isEmpty())
                throw new InvalidTransactionException("cannot find receiver "+transactionRequest.getSender());
            if(!sender.get().getUsername().equals(auth.getName()))
                throw new InvalidTransactionException("logged in user does not match the sender "+transactionRequest.getSender());
            Integer balance = Integer.parseInt(sender.get().getProfile().getBalance());
            Integer amount = Integer.parseInt(transactionRequest.getAmount());
            if(balance < amount)
                throw new InvalidTransactionException("Insufficient Balance");
            balance -= amount;
            sender.get().getProfile().setBalance(balance.toString());
            Integer rec_bal = Integer.parseInt(receiver.get().getProfile().getBalance());
            rec_bal += amount;
            receiver.get().getProfile().setBalance(rec_bal.toString());
            Transactions t = transactionsRepository.save(new Transactions(sender.get().getUsername(),receiver.get().getUsername(),amount.toString(),simpleDateFormat.format(new Date())));
            repository.save(sender.get());
            repository.save(receiver.get());
            return new TransactionResponse(t.getId(),"transaction successful");
        } catch (InvalidTransactionException e){
            return new TransactionResponse(null,e.getMessage());
        }
    }
}
