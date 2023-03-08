package com.megazone.springbootbackend.family.service;

import com.megazone.springbootbackend.family.model.dto.PublicApiDto;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/***************************************************
 * <ul>
 * <li>업무 그룹명 : </li>
 * <li>서브 업무명 : </li>
 * <li>파  일  명 : SpecialDay</li>
 * <li>작  성  자 : mz01-ohyunbk</li>
 * <li>작  성  일 : 2023/02/21</li>
 * <li>설     명 : </li>
 * </ul>
 * <pre>
 * ======================================
 * 작성자             일시                  내용
 * mz01-ohyunbk    2023/02/21 4:14 PM    최초 생성
 * ======================================
 * </pre>
 ***************************************************/
@Service
@RequiredArgsConstructor
@Slf4j
public class SpecialDayService {

  private final WebClient webClient;

  public Flux<PublicApiDto> getSpecialDayInfo(int year, int month, String apiPath) {
    MultiValueMap<String, String> valueMap = new LinkedMultiValueMap<>();
    valueMap.setAll(
        Map.of(
            "solYear", String.format("%4d", year)
            , "solMonth", String.format("%02d", month)
            , "ServiceKey",
            "yX5hqo3LJ1iOprsd9HDRR7rfT8xTL5zRwZXzE7aQE34vtPAzGgyzXr8o7KFeLljivyQ9ilchKNZc/fMgsDlVMQ=="
            , "_type", "json"
            , "numOfRows", "20"
        )
    );
    return webClient.mutate()
        .baseUrl("http://apis.data.go.kr/B090041/openapi/service/SpcdeInfoService")
        .build()
        .get()
        .uri(uriBuilder ->
            uriBuilder.path("/" + apiPath)
                .queryParams(valueMap)
                .build()
        )
        .retrieve()
        .onStatus(HttpStatus::is4xxClientError, clientResponse ->
            Mono.error(
                new RuntimeException(
                    String.format("4xx 외부 요청 오류. statusCode: %s, response: %s, header: %s",
                        clientResponse.rawStatusCode(),
                        clientResponse.bodyToMono(String.class),
                        clientResponse.headers().asHttpHeaders()))
            )
        )
        .onStatus(HttpStatus::is5xxServerError, clientResponse ->
            Mono.error(
                new WebClientResponseException(
                    clientResponse.rawStatusCode(),
                    String.format("5xx 외부 시스템 오류. %s", clientResponse.bodyToMono(String.class)),
                    clientResponse.headers().asHttpHeaders(), null, null
                )
            )
        )
        .bodyToFlux(PublicApiDto.class)
        .retry(2)
        .onErrorContinue(
            (throwable, o) -> log.error("PublicApi Call error: {}", throwable.toString()));
  }
}
