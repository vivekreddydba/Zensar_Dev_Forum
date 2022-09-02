package com.zensar.df.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zensar.df.entity.CategoryEntity;

public interface CategoryRepo extends JpaRepository<CategoryEntity, Long>{

}
