package com.hari.NTAT_TASK.repository;

import com.hari.NTAT_TASK.models.RequestMoney;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestMoneyRepository extends MongoRepository<RequestMoney,String> {
    List<RequestMoney> findByPayer(String payer);
}
