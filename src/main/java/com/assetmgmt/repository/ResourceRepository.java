package com.assetmgmt.repository;

import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.assetmgmt.dto.ResourceDto;
import com.assetmgmt.entity.Resource;

@Repository
@Transactional
public interface ResourceRepository extends JpaRepository<Resource, Long> {
	
	@Query("select new com.assetmgmt.dto.ResourceDto(res.id,res.code,res.name,res.page,res.icon,res.link,res.url,res.checkUrl,res.sequence,res.iconName,res.description,res.permitAll,res.status,par.id) from Resource res "
			+ "left join res.parent par "			
			+ "inner join res.resourceRoleMaps rm "
			+ "left join rm.user usr "
			+ "left join rm.role rol "
			+ "where (usr.id =:userId or rol.id IN :roleIds)")
	public Set<ResourceDto> getUserResources(@Param("userId") Long userId,@Param("roleIds") List<Long> roleIds);
}
