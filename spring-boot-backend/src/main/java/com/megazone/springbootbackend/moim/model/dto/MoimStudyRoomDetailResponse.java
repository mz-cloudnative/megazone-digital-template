package com.megazone.springbootbackend.moim.model.dto;

import com.megazone.springbootbackend.moim.model.entity.MoimStudyRoomDetailEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MoimStudyRoomDetailResponse {

  @Schema(description = "스터디룸 세부내용 ID")
  private Long roomDetailId;

  @Schema(description = "스터디룸 ID")
  private Long roomId;

  @Schema(description = "스터디룸 세부내용")
  private String roomDetailContent;

  @Schema(description = "스터디룸 총 사용가능인원")
  private int roomAbleNum;

  @Schema(description = "스터디룸 생성일자")
  private LocalDateTime createDtt;

  @Schema(description = "스터디룸 변경일자")
  private LocalDateTime updateDtt;

  public static MoimStudyRoomDetailResponse fromEntity(MoimStudyRoomDetailEntity moimStudyRoomDetailEntity) {
    return MoimStudyRoomDetailResponse.builder()
        .roomDetailId(moimStudyRoomDetailEntity.getRoomDetailId())
        .roomId(moimStudyRoomDetailEntity.getMoimStudyRoomEntity().getRoomId())
        .roomDetailContent(moimStudyRoomDetailEntity.getRoomDetailContent())
        .createDtt(moimStudyRoomDetailEntity.getCreateDtt())
        .updateDtt(moimStudyRoomDetailEntity.getUpdateDtt())
        .build();
  }

  @Override
  public String toString() {
    return "MoimStudyRoomDetailResponse{" +
        "roomDetailId=" + roomDetailId +
        ", roomId=" + roomId +
        ", roomDetailContent='" + roomDetailContent + '\'' +
        ", roomAbleNum=" + roomAbleNum +
        ", createDtt=" + createDtt +
        ", updateDtt=" + updateDtt +
        '}';
  }
}
