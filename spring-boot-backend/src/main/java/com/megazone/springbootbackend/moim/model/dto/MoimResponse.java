package com.megazone.springbootbackend.moim.model.dto;

import com.megazone.springbootbackend.moim.model.domain.Status;
import com.megazone.springbootbackend.moim.model.entity.MoimEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MoimResponse {

  @Schema(description = "모임 ID")
  private Long moimId;
  @Schema(description = "모임 명")
  private String moimName;
  @Schema(description = "모임 생성시간")
  private LocalDateTime createDtt;
  @Schema(description = "모임 변경시간")
  private LocalDateTime updateDtt;
  @Schema(description = "모임 상태")
  private Status moimStatus;

  public static MoimResponse fromEntity(MoimEntity moimEntity) {
    return MoimResponse.builder()
        .moimId(moimEntity.getMoimId())
        .moimName(moimEntity.getMoimName())
        .createDtt(moimEntity.getCreateDtt())
        .updateDtt(moimEntity.getUpdateDtt())
        .moimStatus(moimEntity.getMoimStatus())
        .build();
  }
}
