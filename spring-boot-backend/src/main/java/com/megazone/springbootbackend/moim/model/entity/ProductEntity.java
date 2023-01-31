package com.megazone.springbootbackend.moim.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.*;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "product_info")
public class ProductEntity extends BaseTimeEntity{

  @Id
  @Column(name = "product_id", nullable = false)
  private Long productId;

  @Column(name = "product_name", nullable = false, length = 30)
  private String productName;

  @Column(name = "product_price", nullable = false)
  private int productPrice;

  @Column(name = "product_part", nullable = false, length = 50)
  private String productPart;

//  @Column(name = "create_datetime", nullable = false)
//  private LocalDateTime createDtt;
//
//  @Column(name = "update_datetime", nullable = false)
//  private LocalDateTime updateDtt;
}
