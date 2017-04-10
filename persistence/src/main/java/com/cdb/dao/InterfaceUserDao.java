package com.cdb.dao;

import com.cdb.model.entities.User;

public interface InterfaceUserDao {

    User findByUserName(String username);

}
