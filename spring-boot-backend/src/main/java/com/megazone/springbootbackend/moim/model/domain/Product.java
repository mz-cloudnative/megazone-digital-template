package com.megazone.springbootbackend.moim.model.domain;

import java.time.LocalDateTime;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {

  private Long productId;

  private String productName;

  private int productPrice;

  private String productPart;

  private LocalDateTime createDtt;

  private LocalDateTime updateDtt;
}
