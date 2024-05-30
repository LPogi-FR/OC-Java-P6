package com.lpogifr.paymybuddy.config;

import com.lpogifr.paymybuddy.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
@EnableWebSecurity
public class SecurityConfig {

  @Autowired
  private UsersRepository repository;

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    /*return http
      .authorizeHttpRequests(request -> request.anyRequest().permitAll())
      .httpBasic(Customizer.withDefaults())
      .build();*/
    FormLoginConfigurer configurer = http.formLogin();
    return http
      .csrf(csrf -> {
        csrf.disable();
      })
      .cors(cors -> cors.disable())
      .authorizeHttpRequests(auth -> {
        auth.requestMatchers("/").permitAll();
        auth.requestMatchers("/error/**").permitAll();
        auth.requestMatchers("/login").permitAll();
        auth.anyRequest().authenticated();
      })
      //  .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
      // .oauth2ResourceServer(oauth2 -> oauth2.jwt(jwt -> jwt.decoder(jwtDecoder())))
      //.userDetailsService(userDetailsService)
      .httpBasic(Customizer.withDefaults())
      .formLogin(Customizer.withDefaults())
      .build();
  }

  @Bean
  PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
