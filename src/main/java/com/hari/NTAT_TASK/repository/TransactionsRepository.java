package com.hari.NTAT_TASK.repository;

import com.hari.NTAT_TASK.models.Transactions;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionsRepository extends MongoRepository<Transactions, String> {
    @Query(sort = "{ _id : -1 }")
    List<Transactions> findBySender(String sender);

    @Query(sort = "{ _id : -1 }")
    List<Transactions> findByReceiver(String receiver);
}
