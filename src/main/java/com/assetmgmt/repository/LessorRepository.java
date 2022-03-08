package com.assetmgmt.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.assetmgmt.entity.MasterLessor;

@Repository
@Transactional
public interface LessorRepository extends JpaRepository<MasterLessor, Long> {
}
