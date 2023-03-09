package com.megazone.springbootbackend.moim.service;

import com.megazone.springbootbackend.moim.event.KafkaMessageEvent;
import com.megazone.springbootbackend.moim.event.MoimEvent;
import com.megazone.springbootbackend.moim.event.publisher.GenericEventPublisher;
import com.megazone.springbootbackend.moim.slack.cilent.SlackClient;
import com.megazone.springbootbackend.moim.slack.dto.SlackAttachment;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommonFacade {

  private final ApplicationEventPublisher applicationEventPublisher;
  private final GenericEventPublisher<String> genericEventPublisher;

  //  private final MoimService moimService;
//  private final StudyRoomService studyRoomService;
  private final SlackClient slackClient;

  @Transactional
  public void createMoim(String administrator, String moimName) {
    applicationEventPublisher.publishEvent(new MoimEvent(this, moimName, administrator, ""));
  }

  public void genericType(String message, boolean success) {
    genericEventPublisher.publish(message, success);
  }

  public void processMessage(String topic, String message, Long offset) {
    applicationEventPublisher.publishEvent(new KafkaMessageEvent(this, topic, message, offset));
  }

  public void sendMessage(String message) {
    SlackAttachment slackAttachment = SlackAttachment.builder()
        .fallback("요청실패 메시지")
        .color("#2eb886")
        .pretext("attachment block 위에 나타날 text입니다. ")
        .authorName("roseline")
        .authorLink("https://github.com/roseline124")
        .authorIcon(
            "https://avatars.githubusercontent.com/u/41788121?s=460&u=ee6a6f6499aa68a23947cfb76d5e9cb6eebfd29c&v=4")
        .title("attachments 사용법")
        .titleLink("https://api.slack.com/")
        .text(message)
        .imageUrl("https://img.hankyung.com/photo/201806/C0A8CA3C0000015DA7210A57000CEC00_P2.jpg")
        .thumbUrl("https://t1.daumcdn.net/keditor/emoticon/friends1/large/002.gif")
        .footer("Slack API")
        .footerIcon("https://platform.slack-edge.com/img/default_application_icon.png")
        .ts(Long.parseLong("1677139883"))
        .build();
    slackClient.sendMessage(slackAttachment);
  }
}
