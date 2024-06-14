package com.lpogifr.paymybuddy.service.impl;

import com.lpogifr.paymybuddy.entity.UserEntity;
import com.lpogifr.paymybuddy.repository.UsersRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

  private final UsersRepository usersRepository;
  private final PasswordEncoder passwordEncoder;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    UserEntity entity = usersRepository.findByEmail(username);
    entity.setPassword(passwordEncoder.encode(entity.getPassword()));
    return entity;
  }
}
