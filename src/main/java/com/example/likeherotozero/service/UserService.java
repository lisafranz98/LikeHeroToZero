package com.example.likeherotozero.service;

import com.example.likeherotozero.dao.UserDAO;
import com.example.likeherotozero.entity.UserEntity;
import org.mindrot.jbcrypt.BCrypt;

public class UserService {

    private UserDAO userDAO;

    public UserService() {this.userDAO = new UserDAO(); }

    public UserEntity checkLogin(String username, String password)
    {
        UserEntity userEntity = userDAO.findByUsername(username);
        if(userEntity != null && checkPassword(password, userEntity.getPassword()))
        {
            return userEntity;
        }
        return null;
    }

    private String hashPassword(String password)
    {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    private boolean checkPassword(String password, String hashedPassword)
    {
        return BCrypt.checkpw(password, hashedPassword);
    }
}