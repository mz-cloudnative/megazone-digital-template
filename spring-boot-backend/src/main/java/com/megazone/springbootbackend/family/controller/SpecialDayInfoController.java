package com.megazone.springbootbackend.family.controller;

import com.megazone.springbootbackend.family.model.dto.PublicApiDto.Response.Body.Items.Item;
import com.megazone.springbootbackend.family.service.AsyncService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/special-days")
@RequiredArgsConstructor
@Slf4j
public class SpecialDayInfoController {

  private final AsyncService asyncService;

  @GetMapping(value = "/all", produces = "application/json")
  public Flux<Item> getHoliDeInfoAsync() {
    return asyncService.getAll(2023);
  }
}
