package com.megazone.springbootbackend.moim.controller;

import com.megazone.springbootbackend.moim.service.MoimService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class MoimEventController {

  private final MoimService moimService;

  @GetMapping("/event")
  public String moimEventInvite(@RequestParam String userName, @RequestParam String moimName) {
    moimService.inviteEvent(userName, moimName);
    return "good~~";
  }

  @GetMapping("/event/generic")
  public String eventGeneric(@RequestParam String message, @RequestParam boolean success) {
    moimService.genericType(message,success);
    return "gggggooood~~";
  }
}
