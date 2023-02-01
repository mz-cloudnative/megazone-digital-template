package com.megazone.springbootbackend.moim.model.dto;

import com.megazone.springbootbackend.moim.model.domain.Status;
import com.megazone.springbootbackend.moim.model.entity.MoimStudyRoomDetailEntity;
import com.megazone.springbootbackend.moim.model.entity.MoimStudyRoomEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import java.util.List;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MoimStudyRoomResponse {

  @Schema(description = "스터디룸 ID")
  private Long roomId;

  @Schema(description = "스터디룸 명")
  private String roomName;

  @Schema(description = "스터디룸 생성일자")
  private LocalDateTime createDtt;

  @Schema(description = "스터디룸 변경일자")
  private LocalDateTime updateDtt;

  @Schema(description = "스터디룸 상태")
  private Status roomStatus;

  @Schema(description = "스터디룸 세부정보")
  private List<MoimStudyRoomDetailEntity> moimStudyRoomDetailEntityList;

  public static MoimStudyRoomResponse fromEntity(MoimStudyRoomEntity moimStudyRoomEntity) {
    return MoimStudyRoomResponse.builder()
        .roomId(moimStudyRoomEntity.getRoomId())
        .roomName(moimStudyRoomEntity.getRoomName())
        .createDtt(moimStudyRoomEntity.getCreateDtt())
        .updateDtt(moimStudyRoomEntity.getUpdateDtt())
        .roomStatus(moimStudyRoomEntity.getRoomStatus())
        .moimStudyRoomDetailEntityList(moimStudyRoomEntity.getMoimStudyRoomDetailEntityList())
        .build();
  }

  @Override
  public String toString() {
    return "MoimStudyRoomResponse{" +
        "roomId=" + roomId +
        ", roomName='" + roomName + '\'' +
        ", createDtt=" + createDtt +
        ", updateDtt=" + updateDtt +
        ", roomStatus=" + roomStatus +
        ", moimStudyRoomDetailEntityList=" + moimStudyRoomDetailEntityList +
        '}';
  }
}
