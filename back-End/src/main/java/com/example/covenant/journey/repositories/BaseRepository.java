package com.example.covenant.journey.repositories;

import com.example.covenant.journey.model.AbstractEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseRepository<Entity extends AbstractEntity> extends JpaRepository<Entity, Long>, JpaSpecificationExecutor<Entity> {
}
