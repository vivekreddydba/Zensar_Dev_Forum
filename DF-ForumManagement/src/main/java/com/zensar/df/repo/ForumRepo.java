package com.zensar.df.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.zensar.df.entity.ForumEntity;

public interface ForumRepo extends JpaRepository<ForumEntity, Long>{

}
