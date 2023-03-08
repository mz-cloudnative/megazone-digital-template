package com.megazone.springbootbackend.family.service;

import com.megazone.springbootbackend.family.model.dto.PublicApiDto.Response.Body.Items.Item;
import java.util.Comparator;
import java.util.stream.IntStream;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

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

  public Flux<Item> getAll(int year) {

    return Flux.fromArray(IntStream.rangeClosed(1, 12).boxed().toArray(Integer[]::new))
        .flatMapSequential(month -> getSpecialDays(year, month))
        .distinct()
        .sort(Comparator.comparingInt(Item::getLocdate))
        .doOnError(throwable -> log.error("result error: {}", throwable.toString()));
  }

  private Flux<Item> getSpecialDays(int year, int month) {
    return Flux.concat(
            getSpecialDayInfo(year, month, "getHoliDeInfo")
            , getSpecialDayInfo(year, month, "getRestDeInfo")
            , getSpecialDayInfo(year, month, "getAnniversaryInfo")
            , getSpecialDayInfo(year, month, "get24DivisionsInfo")
            , getSpecialDayInfo(year, month, "getSundryDayInfo"))
        .onErrorResume(throwable -> Flux.empty());
  }

  private Flux<Item> getSpecialDayInfo(int year, int month, String apiPath) {
    return specialDayService.getSpecialDayInfo(year, month, apiPath)
        .flatMapIterable(publicApiDto -> publicApiDto.getResponse().getBody().getItems().getItem())
        .onErrorContinue((throwable, o) -> log.error("Item error: {}", throwable.toString()));
  }

}
