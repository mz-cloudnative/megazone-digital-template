package com.megazone.springbootbackend.moim.model.dto;

import com.megazone.springbootbackend.moim.model.entity.MoimDetailEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MoimDetailResponse {

  @Schema(description = "모임 세부내용 ID")
  private Long moimDetailId;

  @Schema(description = "모임 ID")
  private Long moimId;

  @Schema(description = "모임 세부내용")
  private String moimDetailContent;

  @Schema(description = "모임 참석가능인원")
  private int moimDetailableNum;

  @Schema(description = "모임 장소")
  private String moimDetailPlace;

  @Schema(description = "모임 생성시간")
  private LocalDateTime createDtt;

  @Schema(description = "모임 변경시간")
  private LocalDateTime updateDtt;

  public static MoimDetailResponse fromEntity(MoimDetailEntity moimDetailEntity) {
    return MoimDetailResponse.builder()
        .moimDetailId(moimDetailEntity.getMoimDetailId())
        .moimId(moimDetailEntity.getMoimEntity().getMoimId())
        .moimDetailContent(moimDetailEntity.getMoimDetailContent())
        .moimDetailableNum(moimDetailEntity.getMoimDetailableNum())
        .moimDetailPlace(moimDetailEntity.getMoimDetailPlace())
        .createDtt(moimDetailEntity.getCreateDtt())
        .updateDtt(moimDetailEntity.getUpdateDtt())
        .build();
  }
}
