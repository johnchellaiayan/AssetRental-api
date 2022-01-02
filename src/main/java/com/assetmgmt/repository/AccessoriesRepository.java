package com.assetmgmt.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.assetmgmt.entity.Accessories;

@Repository
@Transactional
public interface AccessoriesRepository extends JpaRepository<Accessories, String> {
}
