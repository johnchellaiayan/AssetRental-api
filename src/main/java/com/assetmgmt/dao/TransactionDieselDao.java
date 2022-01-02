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

import com.assetmgmt.entity.TransactionDiesel;
import com.assetmgmt.entity.model.TransactionDieselModel;
import com.assetmgmt.repository.TransactionDieselRepository;

@Repository
public class TransactionDieselDao {

	@Autowired
	private EntityManager em;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private TransactionDieselRepository transactionDieselRepository;

	public TransactionDiesel saveTransactionDiesel(TransactionDieselModel transactionDieselModel) {
		TransactionDiesel transactionDiesel = modelMapper.map(transactionDieselModel, TransactionDiesel.class);
		transactionDieselRepository.save(transactionDiesel);
		return transactionDiesel;
	}

	@Transactional
	public TransactionDiesel updateTransactionDiesel(TransactionDieselModel transactionDieselModel, Long id) {
		transactionDieselModel.setId(id.toString());
		TransactionDiesel transactionDiesel = modelMapper.map(transactionDieselModel, TransactionDiesel.class);
		Session session = em.unwrap(Session.class);
		session.update(transactionDiesel);
		return transactionDiesel;
	}

	@SuppressWarnings("deprecation")
	@Transactional
	public List<TransactionDiesel> getAllTransactionDiesels(int limit, int offset) {
		Session session = em.unwrap(Session.class);
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<TransactionDiesel> criteriaQuery = builder.createQuery(TransactionDiesel.class);
		Root<TransactionDiesel> root = criteriaQuery.from(TransactionDiesel.class);
		criteriaQuery.select(root);
		Query<TransactionDiesel> query = session.createQuery(criteriaQuery);
		List<TransactionDiesel> transactionDiesels = query.setFirstResult(offset).setMaxResults(limit).getResultList();
		return transactionDiesels;
	}

	@Transactional
	@SuppressWarnings({ "deprecation", "unchecked" })
	public List<TransactionDiesel> getTransactionDieselDetail(Long id) {
		Session session = em.unwrap(Session.class);
		Criteria cr = session.createCriteria(TransactionDiesel.class);
		cr.add(Restrictions.eq("id", id));
		List<TransactionDiesel> list = (List<TransactionDiesel>) cr.list();
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

	@Transactional
	@SuppressWarnings({ "deprecation" })
	public List<TransactionDiesel> searchTransactionDieselInfo(String inFindValue) {
		Session session = em.unwrap(Session.class);
		CriteriaBuilder crBuilder = session.getCriteriaBuilder();
		CriteriaQuery<TransactionDiesel> crq = crBuilder.createQuery(TransactionDiesel.class);
		Root<TransactionDiesel> root = crq.from(TransactionDiesel.class);
		crq.select(root)
				.where(crBuilder.or(crBuilder.like(root.<String>get("name"), "" + inFindValue + "%"),
						crBuilder.like(root.get("mobileNo1"), "" + inFindValue + "%"),
						crBuilder.like(root.<String>get("area"), "" + inFindValue + "%"),
						crBuilder.like(root.<String>get("mobileNo2"), "" + inFindValue + "%"),
						crBuilder.like(root.<String>get("phoneNo1"), "" + inFindValue + "%"),
						crBuilder.like(root.<String>get("phoneNo2"), "" + inFindValue + "%")))
				.orderBy(crBuilder.asc(root.get("name")));

		Query<TransactionDiesel> q = session.createQuery(crq);
		List<TransactionDiesel> list = q.getResultList();
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}
}
