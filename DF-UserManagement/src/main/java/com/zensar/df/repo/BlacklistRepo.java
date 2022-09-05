package com.zensar.df.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zensar.df.entity.BlackListEntity;

public interface BlacklistRepo extends JpaRepository<BlackListEntity,Integer> {

}
