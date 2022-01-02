package com.assetmgmt.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.assetmgmt.entity.ApplicationError;

@Repository
@Transactional
public interface ErrorLogRepository extends JpaRepository<ApplicationError, Long> {

}
