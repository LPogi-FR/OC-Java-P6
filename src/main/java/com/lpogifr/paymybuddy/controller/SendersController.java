package com.lpogifr.paymybuddy.controller;

import com.lpogifr.paymybuddy.model.SenderModel;
import com.lpogifr.paymybuddy.service.SendersService;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@AllArgsConstructor
public class SendersController {

  private final SendersService sendersService;

  @GetMapping("/senders")
  public ResponseEntity<List<SenderModel>> findAllSenders() {
    List<SenderModel> response = sendersService.findAll();
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @GetMapping("/senders/{email}")
  public ResponseEntity<SenderModel> findSenderByEmail(@PathVariable(name = "email") String email) {
    SenderModel response = sendersService.findByEmail(email);
    if (response == null) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @PostMapping("/senders")
  public ResponseEntity<SenderModel> save(@RequestBody final SenderModel model) {
    if (model == null) {
      return ResponseEntity.badRequest().build();
    }
    final var response = sendersService.save(model);
    return new ResponseEntity<>(response, HttpStatus.CREATED);
  }

  @DeleteMapping("/senders/{email}")
  public ResponseEntity<SenderModel> delete(@PathVariable(name = "email") String email) {
    if (sendersService.findByEmail(email) == null) {
      return ResponseEntity.notFound().build();
    }
    sendersService.delete(email);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @PutMapping("/senders/{id}")
  public ResponseEntity<SenderModel> update(@PathVariable Long id, @RequestBody final SenderModel model) {
    if (sendersService.findById(id) == null) {
      return ResponseEntity.notFound().build();
    }
    final var response = sendersService.update(id, model);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @PostMapping("/senders/{id}/receiver")
  public ResponseEntity<SenderModel> addreceiver(@PathVariable Long id, @RequestBody final Long receiverId) {
    if (sendersService.findById(id) == null) {
      return ResponseEntity.notFound().build();
    }
    final var response = sendersService.addreceiver(id, receiverId);
    //find receiver(sender) by id
    //add receiver to sender

    return new ResponseEntity<>(response, HttpStatus.OK);
  }
}
