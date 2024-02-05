package com.lpogifr.paymybuddy.controller;

import com.lpogifr.paymybuddy.assembler.Assembler;
import com.lpogifr.paymybuddy.dto.UsersDto;
import com.lpogifr.paymybuddy.model.UserModel;
import com.lpogifr.paymybuddy.service.UsersService;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
  public ResponseEntity<UserModel> findUserByEmail(@PathVariable(name = "email") String email) {
    UserModel response = assembler.userEntityToModel(usersService.findByEmail(email));
    if (response == null) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @PostMapping("/users")
  public ResponseEntity<UserModel> save(@RequestBody final UsersDto dto) {
    final var response = assembler.userEntityToModel(usersService.save(dto));
    return new ResponseEntity<>(response, HttpStatus.CREATED);
  }

  @DeleteMapping("/users/{email}")
  public ResponseEntity<UserModel> delete(@PathVariable(name = "email") String email) {
    usersService.delete(email);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
  /*@PutMapping("/users")
  public ResponseEntity<UserModel> update(@RequestBody final UsersDto dto) {
    final var response = usersService.update(dto);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }*/
}
