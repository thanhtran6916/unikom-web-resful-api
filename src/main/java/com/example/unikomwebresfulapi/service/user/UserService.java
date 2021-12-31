package com.example.unikomwebresfulapi.service.user;

import com.example.unikomwebresfulapi.dto.request.UserRequest;
import com.example.unikomwebresfulapi.dto.response.UserResponse;
import com.example.unikomwebresfulapi.model.User;
import com.example.unikomwebresfulapi.model.UserPrincipal;
import com.example.unikomwebresfulapi.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.Optional;

@Service
public class UserService implements IUserService {

    @Autowired
    public IUserRepository userRepository;

    @Override
    public Page<UserResponse> findAll() {
        return null;
    }

    @Override
    public UserResponse findById(Long id) {
        return null;
    }

    @Override
    public UserResponse save(UserRequest userRequest) {
        return null;
    }

    @Override
    public UserResponse edit(Long id, UserRequest userRequest) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findUserExistByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserExistByUsername(username);
        UserDetails userDetails = UserPrincipal.build(user);
        return userDetails;
    }
}
