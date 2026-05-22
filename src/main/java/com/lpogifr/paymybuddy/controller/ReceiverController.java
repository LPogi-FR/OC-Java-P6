package com.lpogifr.paymybuddy.controller;

import com.lpogifr.paymybuddy.model.SenderModel;
import com.lpogifr.paymybuddy.service.ReceiverService;
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
public class ReceiverController {

  private final ReceiverService receiverService;

  @GetMapping("/receiver")
  public ResponseEntity<List<SenderModel>> findAllreceiver() {
    //List<SenderModel> response = assembler.senderEntityToModel(receiverService.findAll());
    return new ResponseEntity<>(HttpStatus.OK);
  }
}
