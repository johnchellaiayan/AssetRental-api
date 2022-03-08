package com.assetmgmt.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.assetmgmt.entity.MasterLessee;

@Repository
@Transactional
public interface LesseeRepository extends JpaRepository<MasterLessee, Long> {
}
