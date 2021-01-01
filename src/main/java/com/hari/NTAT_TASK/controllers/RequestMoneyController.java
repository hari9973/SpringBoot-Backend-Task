package com.hari.NTAT_TASK.controllers;

import com.hari.NTAT_TASK.models.RequestMoney;
import com.hari.NTAT_TASK.models.RequestMoneyRequest;
import com.hari.NTAT_TASK.models.RequestMoneyResponse;
import com.hari.NTAT_TASK.models.Users;
import com.hari.NTAT_TASK.repository.RequestMoneyRepository;
import com.hari.NTAT_TASK.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
public class RequestMoneyController {

    @Autowired
    private UserRepository repository;

    @Autowired
    private RequestMoneyRepository requestMoneyRepository;

    @GetMapping("/get_requests")
    public List<RequestMoney> getRequests(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        List<RequestMoney> lis = requestMoneyRepository.findByPayer(auth.getName());
        Collections.sort(lis);
        Collections.reverse(lis);
        return lis;
    }

    @PostMapping("/request_money")
    public ResponseEntity<?> requestMoney(@RequestBody RequestMoneyRequest requestMoneyRequest){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.getName().equals(requestMoneyRequest.getRecipient())) {
            Optional<Users> payer = repository.findByusername(requestMoneyRequest.getPayer());
            if (payer.isPresent()) {
                requestMoneyRepository.save(new RequestMoney(requestMoneyRequest.getPayer(), requestMoneyRequest.getRecipient(),requestMoneyRequest.getAmount(),new Date()));
                return ResponseEntity.ok(new RequestMoneyResponse("Request Placed Successfully"));
            } else
                return ResponseEntity.badRequest().body(new RequestMoneyResponse("Payer not present"));
        }
        else
            return ResponseEntity.badRequest().body(new RequestMoneyResponse("Recipient not present"));
    }
}
