package com.boot.service;

import com.boot.persist.entity.User;
import com.boot.persist.repo.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    UsersRepository usersRepository;

    public List<User> getAllProducts(){
        return usersRepository.findAll();
    }

    @Autowired
    public void setUsersRepository(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }
}
