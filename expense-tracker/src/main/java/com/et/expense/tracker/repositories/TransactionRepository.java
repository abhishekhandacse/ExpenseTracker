package com.et.expense.tracker.repositories;

import com.et.expense.tracker.domain.Transaction;
import com.et.expense.tracker.exception.EtBadRequestException;
import com.et.expense.tracker.exception.EtResourceNotFoundException;

import java.util.List;

public interface TransactionRepository {

    List<Transaction> findAll(Integer userId,Integer categoryId) throws EtResourceNotFoundException;
    Transaction findById(Integer userId,Integer categoryId,Integer transactionId) throws EtResourceNotFoundException;

    Integer create(Integer userId,Integer categoryId,Double amount,String note, Long transactionDate) throws EtResourceNotFoundException;

    void update(Integer userId, Integer categoryId,Integer transactionId,Transaction transaction) throws EtBadRequestException;

    void removeById(Integer userId,Integer categoryId,Integer transactionId) throws EtResourceNotFoundException;

}
