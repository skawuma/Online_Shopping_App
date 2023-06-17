package com.onlineshoppers.Online_Shoppers_Backend.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.onlineshoppers.Online_Shoppers_Backend.dao.RoleDao;
import com.onlineshoppers.Online_Shoppers_Backend.entity.Role;

public class RoleService {

    @Autowired
    private RoleDao roleDao;

    public Role createNewRole(Role role) {
        return roleDao.save(role);
    }
    
}
