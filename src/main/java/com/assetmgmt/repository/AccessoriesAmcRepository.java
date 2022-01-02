package com.assetmgmt.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.assetmgmt.entity.AccessoriesAmc;

@Repository
@Transactional
public interface AccessoriesAmcRepository extends JpaRepository<AccessoriesAmc, String> {
}
