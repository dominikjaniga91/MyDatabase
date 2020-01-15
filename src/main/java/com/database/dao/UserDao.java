package com.database.dao;

import com.database.model.User;

public interface UserDao {

    void createUser(User user);

    User getUser(String email);

    void updateUser(User user);


}
