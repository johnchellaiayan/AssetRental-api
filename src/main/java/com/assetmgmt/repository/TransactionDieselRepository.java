package com.assetmgmt.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.assetmgmt.entity.TransactionDiesel;

@Repository
@Transactional
public interface TransactionDieselRepository extends JpaRepository<TransactionDiesel, String> {
}
