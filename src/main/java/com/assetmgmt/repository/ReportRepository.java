package com.assetmgmt.repository;

import com.assetmgmt.dto.ReportAcamcDto;
import com.assetmgmt.dto.ReportDieselDto;
import com.assetmgmt.dto.ReportRentalDto;
import com.assetmgmt.entity.RentalAgreements;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface ReportRepository extends CrudRepository<RentalAgreements, Long> {
	
	/*@Query("select new com.assetmgmt.dto.ReportDto(res.id,res.code,res.name,res.page,res.icon,res.link,res.url,res.checkUrl,res.sequence,res.iconName,res.description,res.permitAll,res.status,par.id) from Resource res "
			+ "left join res.parent par "			
			+ "inner join res.resourceRoleMaps rm "
			+ "left join rm.user usr "
			+ "left join rm.role rol "
			+ "where (usr.id =:userId or rol.id IN :roleIds)")
	public Set<ResourceDto> getUserResources(@Param("userId") Long userId,@Param("roleIds") List<Long> roleIds);*/

	@Query(value = "select  td.diesel_id as dieselId, td.base_amount as baseAmount, td.cgst, td.sgst, td.start_date as startDate, td.end_date as endDate,  " +
			" td.description  from rental_agreements ra "
			+ "left join master_lessor ml on ra.lessor_id =ml.id "
			+ "right join transaction_diesel td on ra.agreement_id =td.agreement_id "
			+ "WHERE  ra.lessee_id = ?1 and  ra.lessor_id = ?2 and td.start_date >= ?3 and td.end_date <= ?4",
			nativeQuery = true) // and r.startRentalPeriod >= ?3 and r.endRentalPeriod <= ?4")
	List<ReportDieselDto> findDieselTransReport(String lesseeId, String lessorId,
												String startRentalPeriod, String endRentalPeriod);

	@Query(value = "select  td.id as accId, td.base_amount as baseAmount, td.cgst, td.sgst, " +
			"td.start_date as startDate, td.end_date as endDate,  " +
			"  td.received_date as receivedDate, td.total_amount as totalAmount" +
			"  from rental_agreements ra "
			+ "left join master_lessor ml on ra.lessor_id =ml.id "
			+ "right join transaction_accessories td on ra.agreement_id =td.agreement_id "
			+ "WHERE  ra.lessee_id = ?1 and  ra.lessor_id = ?2 and td.start_date >= ?3 and td.end_date <= ?4",
			nativeQuery = true) // and r.startRentalPeriod >= ?3 and r.endRentalPeriod <= ?4")
	List<ReportDieselDto> findGensetTransReport(String lesseeId, String lessorId,
												String startRentalPeriod, String endRentalPeriod);

	@Query(value = "select  td.amc_id as amcId, td.base_amount as baseAmount, td.cgst, td.sgst, "
			+ "td.start_date as startDate, td.end_date as endDate, td.model_name as modelName, "
			+  "td.description, td.total_amount as totalAmount  from rental_agreements ra "
			+ "left join master_lessor ml on ra.lessor_id =ml.id "
			+ "right join accessories_amc td on ra.agreement_id =td.agreement_id "
			+ "WHERE  ra.lessee_id = ?1 and  ra.lessor_id = ?2 and td.start_date >= ?3 and td.end_date <= ?4",
			nativeQuery = true) // and r.startRentalPeriod >= ?3 and r.endRentalPeriod <= ?4")
	List<ReportAcamcDto> findAcamcTransReport(String lesseeId, String lessorId,
											  String startRentalPeriod, String endRentalPeriod);

	@Query(value = "select  td.id as rentalId, td.balance_amount as balanceAmount, "
			+ "td.start_date as startDate, td.end_date as endDate, td.rent_amount as rentAmount, "
			+ "td.description  from rental_agreements ra "
			+ "left join master_lessor ml on ra.lessor_id =ml.id "
			+ "right join transaction_rent td on ra.agreement_id =td.agreement_id "
			+ "WHERE  ra.lessee_id = ?1 and  ra.lessor_id = ?2 and td.start_date >= ?3 and td.end_date <= ?4",
			nativeQuery = true)
	List<ReportRentalDto> findRentalAgreementReport(String lesseeId, String lessorId,
													String startRentalPeriod, String endRentalPeriod);

}
