package com.megazone.springbootbackend.sample.repository.impl;

import com.megazone.springbootbackend.sample.model.domain.SampleData;
import com.megazone.springbootbackend.sample.model.entity.QSampleJpaEntity;
import com.megazone.springbootbackend.sample.repository.SampleCustomRepository;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

@Repository
public class SampleCustomRepositoryImpl implements SampleCustomRepository {

  private final JPAQueryFactory jpaQueryFactory;

  public SampleCustomRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
    this.jpaQueryFactory = jpaQueryFactory;
  }

  @Override
  public SampleData findAllByName(String name) {
    QSampleJpaEntity sampleEntity = QSampleJpaEntity.sampleJpaEntity;

    return jpaQueryFactory
        .select(Projections.constructor(SampleData.class, sampleEntity.sampleId, sampleEntity.sampleName, sampleEntity.sampleRegDtt))
        .from(sampleEntity)
        .where(sampleEntity.sampleName.eq(name))
        .fetchOne();
  }
}
