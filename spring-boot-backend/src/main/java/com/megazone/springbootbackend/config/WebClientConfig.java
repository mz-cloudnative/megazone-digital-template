package com.megazone.springbootbackend.config;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

/***************************************************
 * <ul>
 * <li>업무 그룹명 : </li>
 * <li>서브 업무명 : </li>
 * <li>파  일  명 : WebClientConfig</li>
 * <li>작  성  자 : mz01-ohyunbk</li>
 * <li>작  성  일 : 2023/02/20</li>
 * <li>설     명 : </li>
 * </ul>
 * <pre>
 * ======================================
 * 작성자             일시                  내용
 * mz01-ohyunbk    2023/02/20 10:45 AM    최초 생성
 * ======================================
 * </pre>
 ***************************************************/
@Slf4j
@Configuration
public class WebClientConfig {

  @Bean(name = "webClient")
  public WebClient webClient() {

    HttpClient httpClient = HttpClient.create()
        .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 3000)
        .doOnConnected(
            conn -> conn.addHandlerLast(new ReadTimeoutHandler(5))  //sec
                .addHandlerLast(new WriteTimeoutHandler(60)) //sec
        );
//        //Memory 조정: 2M (default 256KB)
//        ExchangeStrategies exchangeStrategies = ExchangeStrategies.builder()
//            .codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(2*1024*1024))
//            .build();

    return WebClient.builder()
        .baseUrl("http://apis.data.go.kr/B090041/openapi/service/SpcdeInfoService")
        .clientConnector(new ReactorClientHttpConnector(httpClient))
        .filter(
            (req, next) -> next.exchange(
                ClientRequest.from(req).header("from", "webclient").build()
            )
        )
        .filter(
            ExchangeFilterFunction.ofRequestProcessor(
                clientRequest -> {
                  log.info(">>>>>>>>>> REQUEST <<<<<<<<<<");
                  log.info("Request: {} {}", clientRequest.method(), clientRequest.url());
                  clientRequest.headers().forEach(
                      (name, values) -> values.forEach(value -> log.info("{} : {}", name, value))
                  );
                  return Mono.just(clientRequest);
                }
            )
        )
        .filter(
            ExchangeFilterFunction.ofResponseProcessor(
                clientResponse -> {
                  log.info(">>>>>>>>>> RESPONSE <<<<<<<<<<");
                  clientResponse.headers().asHttpHeaders().forEach(
                      (name, values) -> values.forEach(value -> log.info("{} : {}", name, value)));
                  if (!clientResponse.statusCode().equals(HttpStatus.OK)) {
                    log.info("Status: Code-{}, Name-{}", clientResponse.statusCode().value(),
                        clientResponse.statusCode().name());
                  }
                  return Mono.just(clientResponse);
                }
            )
        )
//        .exchangeStrategies(exchangeStrategies) //메모리 조정
//        .defaultHeader("user-agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_14_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.87 Safari/537.3")
//        .defaultCookie("httpclient-type", "webclient")
        .build();
  }
}
