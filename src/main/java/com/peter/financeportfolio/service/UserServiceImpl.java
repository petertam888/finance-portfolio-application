package com.peter.financeportfolio.service;
import com.peter.financeportfolio.model.User;
import com.peter.financeportfolio.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
public class UserServiceImpl {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

//    @Override
//    public User createUser(User user){
//        return userRepository.save(user);
//    }
//
//    @Override
//    public User getUserById(Long userId){
//        return userRepository.findById(userId).orElse(null);
//    }
}
