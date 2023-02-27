package com.megazone.springbootbackend.sample;

import com.megazone.springbootbackend.config.WebClientConfig;
import com.megazone.springbootbackend.family.model.dto.PublicApiDto;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

/***************************************************
 * <ul>
 * <li>업무 그룹명 : </li>
 * <li>서브 업무명 : </li>
 * <li>파  일  명 : WebClientTest</li>
 * <li>작  성  자 : mz01-ohyunbk</li>
 * <li>작  성  일 : 2023/02/16</li>
 * <li>설     명 : </li>
 * </ul>
 * <pre>
 * ======================================
 * 작성자             일시                  내용
 * mz01-ohyunbk    2023/02/16 10:27 AM    최초 생성
 * ======================================
 * </pre>
 ***************************************************/
@RestClientTest
@Import({WebClientConfig.class})
@DisplayName("WebClient 테스트")
class WebClientTest {

  @Autowired
  private WebClient webClient;

  @Test
  @DisplayName("WebClient GET Complete 테스트")
  void webClient_GET_complete_Test() {

    for (int i = 1; i <= 12; i++) {
      //given
      MultiValueMap<String, String> valueMap = new LinkedMultiValueMap<>();
      valueMap.setAll(
          Map.of(
              "solYear", "2023"
              , "solMonth", String.format("%02d", i)
              , "ServiceKey",
              "yX5hqo3LJ1iOprsd9HDRR7rfT8xTL5zRwZXzE7aQE34vtPAzGgyzXr8o7KFeLljivyQ9ilchKNZc/fMgsDlVMQ=="
              , "_type", "json"
              , "numOfRows", "20"
          )
      );
      //when
      Mono<PublicApiDto> response = webClient.mutate()
          .baseUrl("http://apis.data.go.kr/B090041/openapi/service/SpcdeInfoService")
          .build()
          .get()
          .uri(uriBuilder ->
              uriBuilder.path("/getHoliDeInfo")
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
                      String.format("5xx 외부 시스템 오류. %s",
                          clientResponse.bodyToMono(String.class)),
                      clientResponse.headers().asHttpHeaders(), null, null
                  )
              )
          )
          //.toEntity(String.class) //응답상태, 헤더, 바디를 함께 객체 형태로 받을 경우 사용
          .bodyToMono(PublicApiDto.class) //바디 정보만 필요한 경우 사용
          ;
      //then
      StepVerifier.create(response)
          .expectSubscription()
          .expectNextMatches(
              publicApiDto -> "00".equals(publicApiDto.getResponse().getHeader().getResultCode())
          )
          .expectComplete()
          .verify();
    }
  }

  @Test
  @DisplayName("WebClient GET Error 테스트")
  void webClient_GET_error_Test() {
    //given
    MultiValueMap<String, String> valueMap = new LinkedMultiValueMap<>();
    valueMap.setAll(
        Map.of(
            "solYear", "2023"
            , "solMonth", "01"
            , "ServiceKey",
            "yX5hqo3LJ1iOprsd9HDRR7rfT8xTL5zRwZXzE7aQE34vtPAzGgyzXr8o7KFeLljivyQ9ilchKNZc/fMgsDlVMQ=="
            , "_type", "json"
            , "numOfRows", "20"
        )
    );

    //when
    Mono<ResponseEntity<String>> response = webClient.mutate()
        .baseUrl("http://apis.data.go.kr/B090041/openapi/service/SpcdeInfoService")
        .build()
        .get()
        .uri(uriBuilder ->
            uriBuilder.path("/getHoliDeo")
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
        .toEntity(String.class) //응답상태, 헤더, 바디를 함께 객체 형태로 받을 경우 사용
        //.bodyToMono(String.class) //바디 정보만 필요한 경우 사용
        ;
    //then
    //error 검증
    StepVerifier.create(response)
        .expectSubscription()
        .expectErrorMatches(throwable ->
            throwable instanceof WebClientResponseException &&
                throwable.getMessage().contains("5xx 외부 시스템 오류")
        )
        .verify();
  }


}
