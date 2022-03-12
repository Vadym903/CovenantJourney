package com.example.covenant.journey.repositories;

import com.example.covenant.journey.models.PrimaryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseRepository<Entity extends PrimaryEntity> extends JpaRepository<Entity, Long>, JpaSpecificationExecutor<Entity> {
}
