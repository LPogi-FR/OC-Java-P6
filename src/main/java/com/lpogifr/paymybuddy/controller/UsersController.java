package com.lpogifr.paymybuddy.controller;

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

  @GetMapping("/users")
  public ResponseEntity<List<UserModel>> findAllUsers() {
    List<UserModel> response = usersService.findAll();
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @GetMapping("/users/{email}")
  public ResponseEntity<UserModel> findUserByEmail(@PathVariable(name = "email") String email) {
    UserModel response = usersService.findByEmail(email);
    if (response == null) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @PostMapping("/users")
  public ResponseEntity<UserModel> save(@RequestBody final UserModel model) {
    if (model == null) {
      return ResponseEntity.badRequest().build();
    }
    final var response = usersService.save(model);
    return new ResponseEntity<>(response, HttpStatus.CREATED);
  }

  @DeleteMapping("/users/{email}")
  public ResponseEntity<UserModel> delete(@PathVariable(name = "email") String email) {
    if (usersService.findByEmail(email) == null) {
      return ResponseEntity.notFound().build();
    }
    usersService.delete(email);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @PutMapping("/users/{id}")
  public ResponseEntity<UserModel> update(@PathVariable Long id, @RequestBody final UserModel model) {
    if (usersService.findById(id) == null) {
      return ResponseEntity.notFound().build();
    }
    final var response = usersService.update(model);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @PostMapping("/users/{id}/friend")
  public ResponseEntity<UserModel> addFriend(@PathVariable Long id, @RequestBody final Long friendId) {
    if (usersService.findById(id) == null) {
      return ResponseEntity.notFound().build();
    }
    final var response = usersService.addFriend(id, friendId);
    //find friend(user) by id
    //add friend to user

    return new ResponseEntity<>(response, HttpStatus.OK);
  }
}
