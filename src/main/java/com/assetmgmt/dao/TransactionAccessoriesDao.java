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

import com.assetmgmt.entity.TransactionAccessories;
import com.assetmgmt.entity.model.TransactionAccessoriesModel;
import com.assetmgmt.repository.TransactionAccessoriesRepository;

@Repository
public class TransactionAccessoriesDao {

	@Autowired
	private EntityManager em;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private TransactionAccessoriesRepository transactionAccessoriesRepository;

	public TransactionAccessories saveTransactionAccessories(TransactionAccessoriesModel transactionAccessoriesModel) {
		TransactionAccessories transactionAccessories = modelMapper.map(transactionAccessoriesModel, TransactionAccessories.class);
		transactionAccessoriesRepository.save(transactionAccessories);
		return transactionAccessories;
	}

	@Transactional
	public TransactionAccessories updateTransactionAccessories(TransactionAccessoriesModel transactionAccessoriesModel, Long id) {
		transactionAccessoriesModel.setId(id.toString());
		TransactionAccessories transactionAccessories = modelMapper.map(transactionAccessoriesModel, TransactionAccessories.class);
		Session session = em.unwrap(Session.class);
		session.update(transactionAccessories);
		return transactionAccessories;
	}

	@SuppressWarnings("deprecation")
	@Transactional
	public List<TransactionAccessories> getAllTransactionAccessoriess(int limit, int offset) {
		Session session = em.unwrap(Session.class);
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<TransactionAccessories> criteriaQuery = builder.createQuery(TransactionAccessories.class);
		Root<TransactionAccessories> root = criteriaQuery.from(TransactionAccessories.class);
		criteriaQuery.select(root);
		Query<TransactionAccessories> query = session.createQuery(criteriaQuery);
		List<TransactionAccessories> transactionAccessoriess = query.setFirstResult(offset).setMaxResults(limit).getResultList();
		return transactionAccessoriess;
	}

	@Transactional
	@SuppressWarnings({ "deprecation", "unchecked" })
	public List<TransactionAccessories> getTransactionAccessoriesDetail(Long id) {
		Session session = em.unwrap(Session.class);
		Criteria cr = session.createCriteria(TransactionAccessories.class);
		cr.add(Restrictions.eq("id", id));
		List<TransactionAccessories> list = (List<TransactionAccessories>) cr.list();
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

	@Transactional
	@SuppressWarnings({ "deprecation" })
	public List<TransactionAccessories> searchTransactionAccessoriesInfo(String inFindValue) {
		Session session = em.unwrap(Session.class);
		CriteriaBuilder crBuilder = session.getCriteriaBuilder();
		CriteriaQuery<TransactionAccessories> crq = crBuilder.createQuery(TransactionAccessories.class);
		Root<TransactionAccessories> root = crq.from(TransactionAccessories.class);
		crq.select(root)
				.where(crBuilder.or(crBuilder.like(root.<String>get("name"), "" + inFindValue + "%"),
						crBuilder.like(root.get("mobileNo1"), "" + inFindValue + "%"),
						crBuilder.like(root.<String>get("area"), "" + inFindValue + "%"),
						crBuilder.like(root.<String>get("mobileNo2"), "" + inFindValue + "%"),
						crBuilder.like(root.<String>get("phoneNo1"), "" + inFindValue + "%"),
						crBuilder.like(root.<String>get("phoneNo2"), "" + inFindValue + "%")))
				.orderBy(crBuilder.asc(root.get("name")));

		Query<TransactionAccessories> q = session.createQuery(crq);
		List<TransactionAccessories> list = q.getResultList();
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}
}
