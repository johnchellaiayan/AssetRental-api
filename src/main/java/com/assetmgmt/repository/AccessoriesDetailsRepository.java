package com.assetmgmt.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.assetmgmt.entity.AccessoriesDetails;
import com.assetmgmt.entity.RentalAgreements;

@Repository
@Transactional
public interface AccessoriesDetailsRepository extends JpaRepository<AccessoriesDetails, String> {
}
