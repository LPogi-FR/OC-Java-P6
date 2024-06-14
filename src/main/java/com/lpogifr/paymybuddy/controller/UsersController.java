package com.lpogifr.paymybuddy.controller;

import com.lpogifr.paymybuddy.assembler.Assembler;
import com.lpogifr.paymybuddy.model.UserModel;
import com.lpogifr.paymybuddy.service.UsersService;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@AllArgsConstructor
public class UsersController {

  private final UsersService usersService;
  private final Assembler assembler;

  @GetMapping("/users")
  public ResponseEntity<List<UserModel>> findAllUsers() {
    List<UserModel> response = assembler.userEntityToModel(usersService.findAll());
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @GetMapping("/users/{email}")
  public ResponseEntity<UserModel> findUserById(@PathVariable(name = "email") String email) {
    UserModel response = assembler.userEntityToModel(usersService.findByEmail(email));
    if (response == null) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<>(response, HttpStatus.OK);
  }
}
