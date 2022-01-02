package com.assetmgmt.dao;

import java.time.Duration;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.assetmgmt.entity.RentalAgreements;
import com.assetmgmt.entity.model.RentalAgreementsModel;
import com.assetmgmt.repository.RentalAgreementsRepository;

@Repository
public class RentalAgreementsDao {

	@Autowired
	private EntityManager em;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private RentalAgreementsRepository rentalAgreementsRepository;

	public RentalAgreements saveRentalAgreements(RentalAgreementsModel rentalAgreementsModel) {
		RentalAgreements rentalAgreements = modelMapper.map(rentalAgreementsModel, RentalAgreements.class);
		rentalAgreementsRepository.save(rentalAgreements);
		return rentalAgreements;
	}

	@Transactional
	public RentalAgreements updateRentalAgreements(RentalAgreementsModel rentalAgreementsModel, Long id) {
		rentalAgreementsModel.setId(id);
		RentalAgreements rentalAgreements = modelMapper.map(rentalAgreementsModel, RentalAgreements.class);
		Session session = em.unwrap(Session.class);
		session.update(rentalAgreements);
		return rentalAgreements;
	}
	
	@SuppressWarnings("deprecation")
	@Transactional
	public List<RentalAgreements> getAllRentalAgreementss(int limit,int offset) {
		Session session = em.unwrap(Session.class);
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<RentalAgreements> criteriaQuery = builder.createQuery(RentalAgreements.class);
		Root<RentalAgreements> root = criteriaQuery.from(RentalAgreements.class);
		criteriaQuery.select(root);
		Query<RentalAgreements> query = session.createQuery(criteriaQuery);
		List<RentalAgreements> rentalAgreementss = query.setFirstResult(offset).setMaxResults(limit).getResultList();
		return rentalAgreementss;
	}

	@Transactional
	@SuppressWarnings({ "unchecked", "deprecation" })
	public List<RentalAgreements> getRentalAgreementsDetail(Long id) {
		Session session = em.unwrap(Session.class);
		Criteria cr = session.createCriteria(RentalAgreements.class);
		cr.add(Restrictions.eq("id", id));
		List<RentalAgreements> list = (List<RentalAgreements>) cr.list();
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

	@Transactional
	@SuppressWarnings({ "unchecked", "deprecation" })
	public List<RentalAgreements> searchRentalAgreementsInfo(String inFindField, String inFindValue) {
		Session session = em.unwrap(Session.class);
		Criteria cr = session.createCriteria(RentalAgreements.class);
		cr.add(Restrictions.ilike(inFindField, "%" + inFindValue + "%", MatchMode.ANYWHERE));
		List<RentalAgreements> list = (List<RentalAgreements>) cr.list();
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}
	
	@Transactional
	@SuppressWarnings({ "unchecked", "deprecation" })
	public List<RentalAgreements> getActiveRentalAgreementss(String value) {
		Session session = em.unwrap(Session.class);
		Criteria cr = session.createCriteria(RentalAgreements.class);
		cr.add(Restrictions.ilike("name", "%" + value + "%", MatchMode.ANYWHERE));
		cr.add(Restrictions.eqOrIsNull("isResigned", "true"));
		List<RentalAgreements> list = (List<RentalAgreements>) cr.list();
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}
	
	@Transactional
	@SuppressWarnings({ "unchecked", "deprecation" })
	public List<RentalAgreements> getLiscenseExpiredRentalAgreementss() {
		Session session = em.unwrap(Session.class);
		Date currentDate = new Date();
		Date thirtyDaysFromCurrentDate = new Date(currentDate.getTime() + Duration.ofDays(30).toMillis());
		Criteria cr = session.createCriteria(RentalAgreements.class).add(Restrictions.disjunction().
		add(Restrictions.le("licenseExpiryDate", thirtyDaysFromCurrentDate)).
		add(Restrictions.le("licenseExpiryDate", new Date())));
		List<RentalAgreements> list = (List<RentalAgreements>) cr.list();
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}
}
