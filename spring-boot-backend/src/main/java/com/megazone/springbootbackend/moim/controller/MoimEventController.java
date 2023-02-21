package com.megazone.springbootbackend.moim.controller;

import com.megazone.springbootbackend.moim.service.CommonFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class MoimEventController {

  private final CommonFacade commonFacade;

  @GetMapping("/event")
  public String moimEventInvite(@RequestParam String administrator, @RequestParam String moimName) {
    commonFacade.createMoim(administrator, moimName);
    return String.format("모임이 생성됩니다. 모임명[%s]", moimName);
  }

  @GetMapping("/event/generic")
  public String eventGeneric(@RequestParam String message, @RequestParam boolean success) {
    commonFacade.genericType(message,success);
    return "gggggooood~~";
  }
}
