package com.example.unikomwebresfulapi.service.user;

import com.example.unikomwebresfulapi.model.User;
import com.example.unikomwebresfulapi.model.UserPrincipal;
import com.example.unikomwebresfulapi.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Iterable<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public boolean delete(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (!userOptional.isPresent()) {
            return false;
        }
        User user = userOptional.get();
        user.setDeleteAt(LocalTime.now());
        userRepository.save(user);
        return false;
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
