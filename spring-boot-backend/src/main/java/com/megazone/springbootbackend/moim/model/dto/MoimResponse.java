package com.megazone.springbootbackend.moim.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.megazone.springbootbackend.moim.model.domain.Status;
import com.megazone.springbootbackend.moim.model.entity.MoimDetailEntity;
import com.megazone.springbootbackend.moim.model.entity.MoimEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import java.util.List;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MoimResponse{

  @Schema(description = "모임 ID")
  private Long moimId;
  @Schema(description = "모임 명")
  private String moimName;
  @Schema(description = "모임 관리자")
  private String moimAdministrator;
  @Schema(description = "모임 생성일자")
  private LocalDateTime createDtt;
  @Schema(description = "모임 변경일자")
  private LocalDateTime updateDtt;
  @Schema(description = "모임 상태")
  private Status moimStatus;

  @JsonIgnore
  @Schema(description = "모임 세부정보")
  private List<MoimDetailEntity> moimDetailEntityList;

  public static MoimResponse fromEntity(MoimEntity moimEntity) {
    return MoimResponse.builder()
        .moimId(moimEntity.getMoimId())
        .moimName(moimEntity.getMoimName())
        .moimAdministrator(moimEntity.getMoimAdministrator())
        .createDtt(moimEntity.getCreateDtt())
        .updateDtt(moimEntity.getUpdateDtt())
        .moimStatus(moimEntity.getMoimStatus())
        .moimDetailEntityList(moimEntity.getMoimDetailEntity())
        .build();
  }

  @Override
  public String toString() {
    return "MoimResponse{" +
        "moimId=" + moimId +
        ", moimName='" + moimName +
        ", moimAdministrator='" + moimAdministrator +
        ", createDtt=" + createDtt +
        ", updateDtt=" + updateDtt +
        ", moimStatus=" + moimStatus +
        ", moimDetailEntityList=" + moimDetailEntityList +
        '}';
  }
}
