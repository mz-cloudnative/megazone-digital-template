package com.megazone.springbootbackend.moim.slack.cilent;

import com.megazone.springbootbackend.moim.slack.dto.SlackAttachment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Slack notification 을 사용을 하지 않을 수 있어, url default 을 공백으로 한다.
 */
@FeignClient(name = "slack-api", url = "${external.slack.url}")
public interface SlackClient {

  @PostMapping
  String sendMessage(@RequestBody SlackAttachment slackAttachment);
}
