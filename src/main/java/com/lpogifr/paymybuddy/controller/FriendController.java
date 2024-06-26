package com.lpogifr.paymybuddy.controller;

import com.lpogifr.paymybuddy.model.FriendModel;
import com.lpogifr.paymybuddy.model.UserModel;
import com.lpogifr.paymybuddy.service.FriendService;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@AllArgsConstructor
public class FriendController {

  private final FriendService friendService;

  @GetMapping("/friend")
  public ResponseEntity<List<UserModel>> findAllFriend() {
    //List<UserModel> response = assembler.userEntityToModel(friendService.findAll());
    return new ResponseEntity<>(HttpStatus.OK);
  }
}
