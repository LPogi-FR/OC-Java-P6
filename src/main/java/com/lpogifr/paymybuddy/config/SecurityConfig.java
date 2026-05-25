package com.lpogifr.paymybuddy.config;

/*
import com.lpogifr.paymybuddy.repository.SendersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
@EnableWebSecurity
public class SecurityConfig {

  @Autowired
  private SendersRepository repository;

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    /*return http
      .authorizeHttpRequests(request -> request.anyRequest().permitAll())
      .httpBasic(Customizer.withDefaults())
      .build();*//*
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
        auth.requestMatchers("/register").permitAll();
        auth.anyRequest().authenticated();
      })
      //  .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
      // .oauth2ResourceServer(oauth2 -> oauth2.jwt(jwt -> jwt.decoder(jwtDecoder())))
      //.senderDetailsService(senderDetailsService)
      .httpBasic(Customizer.withDefaults())
      .formLogin(form -> form.loginPage("/login").failureUrl("/login?error=true"))
      .logout(logout -> logout.logoutSuccessUrl("/login?logout=true").deleteCookies("JSESSIONID").logoutUrl("/logout"))
      .build();
  }

}
        */
import com.lpogifr.paymybuddy.repository.SendersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Spring Security configuration to set authentication and authorization parameters on specific pages of the app.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

  /**
   * Bean of the filter chain.
   *
   * @param http The parameter used to configure the web security.
   * @return The configuration of the filter chain.
   */
  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    return http
      .authorizeHttpRequests(auth ->
        auth
          .requestMatchers("/login", "/register", "/css/**", "/js/**", "/images/**")
          .permitAll()
          .anyRequest()
          .authenticated()
      )
      .formLogin(form ->
        form.loginPage("/login").defaultSuccessUrl("/home", true).failureUrl("/login?error=true").permitAll()
      )
      .logout(logout -> logout.logoutUrl("/logout").logoutSuccessUrl("/login?logout").permitAll())
      .build();
  }

  @Bean
  PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
  /*
  /**
   * Bean of the authentication rules.
   *
   * @param http                  The parameter used to configure the web security.
   * @param bCryptPasswordEncoder The password encoder.
   * @return The authentication configuration.
   */
  //  @Bean
  //  public AuthenticationManager authenticationManager(HttpSecurity http, BCryptPasswordEncoder bCryptPasswordEncoder) {
  //    AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(
  //      AuthenticationManagerBuilder.class
  //    );
  //    authenticationManagerBuilder.userDetailsService(customUserDetailsService).passwordEncoder(bCryptPasswordEncoder);
  //
  //    return authenticationManagerBuilder.build();
  //  }

}
