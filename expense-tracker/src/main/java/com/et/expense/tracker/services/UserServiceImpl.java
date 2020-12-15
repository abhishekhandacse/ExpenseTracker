package com.et.expense.tracker.services;

import com.et.expense.tracker.domain.User;
import com.et.expense.tracker.exception.ETAuthException;
import com.et.expense.tracker.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.regex.Pattern;

@Service
@Transactional
public class UserServiceImpl implements UserService  {

    @Autowired
    UserRepository userRepository;

    @Override
    public User validateUser(String email, String password) throws ETAuthException {
         if(email!=null)email=email.toLowerCase();
         return userRepository.findByEmailAndPassword(email,password);
    }

    @Override
    public User registerUser(String firstName, String lastName, String email, String password) throws ETAuthException {
        Pattern pattern= Pattern.compile("^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$");
        if(email!=null)email=email.toLowerCase();
        if(!pattern.matcher(email).matches())
            throw new ETAuthException("Invalid Email Address");

        Integer count= userRepository.getCountByEmail(email);
        if(count>0)
            throw new ETAuthException("Email Already in use");

        Integer userId=userRepository.create(firstName, lastName, email, password);

        return userRepository.findById(userId);
    }
}
