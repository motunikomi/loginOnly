package com.login.service;

import com.login.model.User;
import com.login.repository.UserRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class UserService {
  @Autowired
  UserRepository userRepository;

  public List<User> findAll() {
    return userRepository.findAll();
  }

  public User findOne(int id) {
    return userRepository.findById(id).orElse(null);
  }

  public User save(User chemical) {
    return userRepository.save(chemical);
  }

  public void delete(int id) {
    userRepository.deleteById(id);
  }
}
