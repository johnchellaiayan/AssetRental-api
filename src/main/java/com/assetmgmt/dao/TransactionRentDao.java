package com.assetmgmt.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.assetmgmt.entity.TransactionRent;
import com.assetmgmt.entity.model.TransactionRentModel;
import com.assetmgmt.repository.TransactionRentRepository;

@Repository
public class TransactionRentDao {

	@Autowired
	private EntityManager em;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private TransactionRentRepository transactionRentRepository;

	public TransactionRent saveTransactionRent(TransactionRentModel transactionRentModel) {
		TransactionRent transactionRent = modelMapper.map(transactionRentModel, TransactionRent.class);
		transactionRentRepository.save(transactionRent);
		return transactionRent;
	}

	@Transactional
	public TransactionRent updateTransactionRent(TransactionRentModel transactionRentModel, Long id) {
		transactionRentModel.setId(id.toString());
		TransactionRent transactionRent = modelMapper.map(transactionRentModel, TransactionRent.class);
		Session session = em.unwrap(Session.class);
		session.update(transactionRent);
		return transactionRent;
	}

	@SuppressWarnings("deprecation")
	@Transactional
	public List<TransactionRent> getAllTransactionRents(int limit, int offset) {
		Session session = em.unwrap(Session.class);
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<TransactionRent> criteriaQuery = builder.createQuery(TransactionRent.class);
		Root<TransactionRent> root = criteriaQuery.from(TransactionRent.class);
		criteriaQuery.select(root);
		Query<TransactionRent> query = session.createQuery(criteriaQuery);
		List<TransactionRent> transactionRents = query.setFirstResult(offset).setMaxResults(limit).getResultList();
		return transactionRents;
	}

	@Transactional
	@SuppressWarnings({ "deprecation", "unchecked" })
	public List<TransactionRent> getTransactionRentDetail(Long id) {
		Session session = em.unwrap(Session.class);
		Criteria cr = session.createCriteria(TransactionRent.class);
		cr.add(Restrictions.eq("id", id));
		List<TransactionRent> list = (List<TransactionRent>) cr.list();
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

	@Transactional
	@SuppressWarnings({ "deprecation" })
	public List<TransactionRent> searchTransactionRentInfo(String inFindValue) {
		Session session = em.unwrap(Session.class);
		CriteriaBuilder crBuilder = session.getCriteriaBuilder();
		CriteriaQuery<TransactionRent> crq = crBuilder.createQuery(TransactionRent.class);
		Root<TransactionRent> root = crq.from(TransactionRent.class);
		crq.select(root)
				.where(crBuilder.or(crBuilder.like(root.<String>get("name"), "" + inFindValue + "%"),
						crBuilder.like(root.get("mobileNo1"), "" + inFindValue + "%"),
						crBuilder.like(root.<String>get("area"), "" + inFindValue + "%"),
						crBuilder.like(root.<String>get("mobileNo2"), "" + inFindValue + "%"),
						crBuilder.like(root.<String>get("phoneNo1"), "" + inFindValue + "%"),
						crBuilder.like(root.<String>get("phoneNo2"), "" + inFindValue + "%")))
				.orderBy(crBuilder.asc(root.get("name")));

		Query<TransactionRent> q = session.createQuery(crq);
		List<TransactionRent> list = q.getResultList();
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}
}
