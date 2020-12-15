package com.et.expense.tracker.repositories;

import com.et.expense.tracker.domain.User;
import com.et.expense.tracker.exception.ETAuthException;

public interface UserRepository {

    Integer create(String firstName,String lastName,String email,String password) throws ETAuthException;

    User findByEmailAndPassword(String email, String password) throws ETAuthException;

    Integer getCountByEmail(String email);

    User findById(Integer userId);
}
