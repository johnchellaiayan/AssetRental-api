package com.assetmgmt.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.assetmgmt.entity.MasterAssets;

@Repository
@Transactional
public interface AssetsRepository extends JpaRepository<MasterAssets, String> {
}
