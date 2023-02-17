package com.megazone.springbootbackend.sample;

import java.util.Map;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

/***************************************************
 * <ul>
 * <li>업무 그룹명 : </li>
 * <li>서브 업무명 : </li>
 * <li>파  일  명 : RestTemplateTest</li>
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
@DisplayName("RestTemplate 테스트")
public class RestTemplateTest {

  private static RestTemplate restTemplate;

  @Test
  @DisplayName("RestTemplate GET 테스트")
  void restTempplate_GET_Test() {
    //given
    restTemplate = new RestTemplate();

    MultiValueMap<String, String> valueMap = new LinkedMultiValueMap();
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

    UriComponents uri = UriComponentsBuilder
        .fromUriString(
            "http://apis.data.go.kr/B090041/openapi/service/SpcdeInfoService/getHoliDeInfo")
        .queryParams(valueMap)
        .build(false);

    //when
    ResponseEntity<String> response =
        restTemplate.getForEntity(
            uri.toUri()
            , String.class
        );

    //then
    Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    Assertions.assertThat(response.getHeaders()).isNotNull();
    Assertions.assertThat(response.getBody()).isNotNull();
  }

  @Test
  @DisplayName("RestTemplate EXCHANGE GET 테스트")
  void restTempplate_EXCHANGE_GET_Test() {
    //given
    restTemplate = new RestTemplate();

    MultiValueMap<String, String> valueMap = new LinkedMultiValueMap();
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

    UriComponents uri =
        UriComponentsBuilder
            .fromUriString(
                "http://apis.data.go.kr/B090041/openapi/service/SpcdeInfoService/getHoliDeInfo")
            .queryParams(valueMap)
            .build(false);

    //when
    ResponseEntity<String> response =
        restTemplate.exchange(uri.toUri(),
            HttpMethod.GET,
            null,
            String.class);

    //then
    Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    Assertions.assertThat(response.getHeaders()).isNotNull();
    Assertions.assertThat(response.getBody()).isNotNull();
  }

}
