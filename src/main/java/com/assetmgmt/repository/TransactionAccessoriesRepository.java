package com.assetmgmt.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.assetmgmt.entity.TransactionAccessories;

@Repository
@Transactional
public interface TransactionAccessoriesRepository extends JpaRepository<TransactionAccessories, String> {
}
