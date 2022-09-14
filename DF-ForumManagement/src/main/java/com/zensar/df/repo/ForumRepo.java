package com.zensar.df.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.zensar.df.dto.ForumDto;
import com.zensar.df.entity.ForumEntity;

public interface ForumRepo extends JpaRepository<ForumEntity, Long>{
	public List<ForumEntity> findBycategory_id(long categoryid);
	public boolean existsBycategory_id(long categoryid);
	public List<ForumEntity> findByusername(String username);
	
	@Query("SELECT text FROM ForumEntity text WHERE " +
            "text.question LIKE CONCAT('%',:searchText, '%')")
    List<ForumEntity> findByText(String searchText);
	
	
	
	
}
