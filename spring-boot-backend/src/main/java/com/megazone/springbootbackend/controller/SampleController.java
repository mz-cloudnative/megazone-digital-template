package com.megazone.springbootbackend.controller;

import com.megazone.springbootbackend.sampleAPI.model.domain.SampleData;
import com.megazone.springbootbackend.sampleAPI.service.SampleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sample")
@RequiredArgsConstructor
@Slf4j
public class SampleController {

  private final SampleService sampleService;

  @GetMapping("/name-detail")
  public @ResponseBody SampleData getTestName(@RequestParam("name") String name) {
    return sampleService.getData(name);
  }
}
