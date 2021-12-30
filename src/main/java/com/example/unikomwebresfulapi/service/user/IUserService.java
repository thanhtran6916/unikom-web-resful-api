package com.example.unikomwebresfulapi.service.user;

import com.example.unikomwebresfulapi.model.User;
import com.example.unikomwebresfulapi.service.IGeneralService;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface IUserService extends IGeneralService<User>, UserDetailsService {

    User findByUsername(String username);
}
