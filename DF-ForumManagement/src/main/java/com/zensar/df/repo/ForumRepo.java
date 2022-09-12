package com.zensar.df.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zensar.df.entity.ForumEntity;

public interface ForumRepo extends JpaRepository<ForumEntity, Long>{
	public List<ForumEntity> findBycategory_id(long categoryid);
	public boolean existsBycategory_id(long categoryid);

}
