package com.megazone.springbootbackend.family.service;

import com.megazone.springbootbackend.family.model.dto.PublicApiDto.Response.Body.Items.Item;
import java.util.Comparator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

/***************************************************
 * <ul>
 * <li>업무 그룹명 : </li>
 * <li>서브 업무명 : </li>
 * <li>파  일  명 : AsyncService</li>
 * <li>작  성  자 : mz01-ohyunbk</li>
 * <li>작  성  일 : 2023/02/21</li>
 * <li>설     명 : </li>
 * </ul>
 * <pre>
 * ======================================
 * 작성자             일시                  내용
 * mz01-ohyunbk    2023/02/21 3:52 PM    최초 생성
 * ======================================
 * </pre>
 ***************************************************/

@Service
@RequiredArgsConstructor
@Slf4j
public class AsyncService {

  private final SpecialDayService specialDayService;

  //@Async("getAsyncExecutor") //webClient 사용시 불필요. RestTemplate + Async 형태로 사용해야 Webclient와 동일.
  public Flux<Item> getAll(int year) {
    return Flux.range(1, 12)
        .parallel()
        .runOn(Schedulers.boundedElastic())
        .flatMap(month -> getSpecialDays(year, month))
        .sequential()
        .sort(Comparator.comparingInt(Item::getLocdate))
        .distinct()
        .onErrorContinue((throwable, o) -> log.error("Item error: {}", throwable.toString()));
  }

  private Flux<Item> getSpecialDays(int year, int month) {
    Flux<Item> specialDayFlux = Flux.empty();

    specialDayFlux = specialDayFlux.concatWith(getSpecialDayInfo(year, month, "getHoliDeInfo"));
    specialDayFlux = specialDayFlux.concatWith(getSpecialDayInfo(year, month, "getRestDeInfo"));
    specialDayFlux = specialDayFlux.concatWith(getSpecialDayInfo(year, month, "getAnniversaryInfo"));
    specialDayFlux = specialDayFlux.concatWith(getSpecialDayInfo(year, month, "get24DivisionsInfo"));
    specialDayFlux = specialDayFlux.concatWith(getSpecialDayInfo(year, month, "getSundryDayInfo"));

    return specialDayFlux;
  }

  private Flux<Item> getSpecialDayInfo(int year, int month, String apiName) {
    return specialDayService.getSpecialDayInfo(year, month, apiName)
        .flatMap(publicApiDto -> Flux.fromIterable(
            publicApiDto.getResponse().getBody().getItems().getItem()))
        .onErrorContinue((throwable, o) -> log.error("Item error: {}", throwable.toString()));
  }

}
