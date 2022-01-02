package com.assetmgmt.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.Tuple;
import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.assetmgmt.dto.IBasicModel;
import com.assetmgmt.entity.User;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Long> {
	@Query("select u.password as password,role.name as roleName from User u left join u.roles role where u.email=:email ")
	List<Tuple> checkByEmail(@Param("email") String email);

	Optional<User> findByEmail(String email);	
	
	@Query("select r.id as id,r.name as name from User usr join usr.roles r where usr.id =:userId")
	public List<IBasicModel> getAllUserRoles(@Param("userId") Long userId);
}
