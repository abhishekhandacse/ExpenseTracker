package com.et.expense.tracker.services;

import com.et.expense.tracker.domain.User;
import com.et.expense.tracker.exception.ETAuthException;

public interface UserService {

    User validateUser(String email,String password) throws ETAuthException;

    User registerUser(String firstName,String lastName,String email,String password) throws ETAuthException;

}
