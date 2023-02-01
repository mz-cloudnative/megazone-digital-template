package com.megazone.springbootbackend.sample.model.entity;

import com.megazone.springbootbackend.sample.model.dto.SampleDataRequest;
import javax.persistence.Embedded;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "sample")
public class SampleJpaEntity {

    @Id
    @Column(name = "id", nullable = false)
    private Long sampleId;

    @Column(name = "name", nullable = false)
    private String sampleName;

    @Column(name = "regDtt", nullable = false)
    private LocalDateTime sampleRegDtt;

    public static SampleJpaEntity requestToEntity(SampleDataRequest param){
        return SampleJpaEntity.builder()
                .sampleId(param.getSampleId())
                .sampleName(param.getSampleName())
                .sampleRegDtt(LocalDateTime.now())
                .build();
    }

}
