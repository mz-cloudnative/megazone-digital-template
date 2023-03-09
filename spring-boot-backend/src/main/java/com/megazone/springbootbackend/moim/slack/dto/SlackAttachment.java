package com.megazone.springbootbackend.moim.slack.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SlackAttachment {

  private String fallback;

  private String color; // good, warning, danger, hex value(e.g. #2eb886)

  private String pretext;

  @JsonProperty("author_name")
  private String authorName;

  @JsonProperty("author_link")
  private String authorLink;

  @JsonProperty("author_icon")
  private String authorIcon;

  private String title;

  @JsonProperty("title_link")
  private String titleLink;

  private String text;

  @JsonProperty("image_url")
  private String imageUrl;

  @JsonProperty("thumb_url")
  private String thumbUrl;

  @JsonProperty("footer")
  private String footer;

  @JsonProperty("footer_icon")
  private String footerIcon;

  private Long ts;

  @Builder
  public SlackAttachment(String fallback, String color, String pretext, String authorName,
      String authorLink, String authorIcon, String title, String titleLink, String text,
      String imageUrl, String thumbUrl, String footer, String footerIcon, Long ts) {
    this.fallback = fallback;
    this.color = color;
    this.pretext = pretext;
    this.authorName = authorName;
    this.authorLink = authorLink;
    this.authorIcon = authorIcon;
    this.title = title;
    this.titleLink = titleLink;
    this.text = text;
    this.imageUrl = imageUrl;
    this.thumbUrl = thumbUrl;
    this.footer = footer;
    this.footerIcon = footerIcon;
    this.ts = ts;
  }

  public static SlackAttachment of(String text) {
    return SlackAttachment.builder()
        .text(text)
        .build();
  }

}
