package com.lpogifr.paymybuddy.service.impl;

import com.lpogifr.paymybuddy.entity.SenderEntity;
import com.lpogifr.paymybuddy.repository.SendersRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SenderDetailsServiceImpl implements UserDetailsService {

  private final SendersRepository sendersRepository;
  private final PasswordEncoder passwordEncoder;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    SenderEntity entity = sendersRepository.findByEmail(username);

    entity.setPassword(passwordEncoder.encode(entity.getPassword()));
    return entity;
  }
}
