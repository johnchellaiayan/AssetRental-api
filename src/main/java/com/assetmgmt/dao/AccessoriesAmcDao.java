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

import com.assetmgmt.entity.AccessoriesAmc;
import com.assetmgmt.entity.model.AccessoriesAmcModel;
import com.assetmgmt.repository.AccessoriesAmcRepository;

@Repository
public class AccessoriesAmcDao {

	@Autowired
	private EntityManager em;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private AccessoriesAmcRepository accessoriesAmcRepository;

	public AccessoriesAmc saveAccessoriesAmc(AccessoriesAmcModel accessoriesAmcModel) {
		AccessoriesAmc accessoriesAmc = modelMapper.map(accessoriesAmcModel, AccessoriesAmc.class);
		accessoriesAmcRepository.save(accessoriesAmc);
		return accessoriesAmc;
	}

	@Transactional
	public AccessoriesAmc updateAccessoriesAmc(AccessoriesAmcModel accessoriesAmcModel, Long id) {
		accessoriesAmcModel.setId(id.toString());
		AccessoriesAmc accessoriesAmc = modelMapper.map(accessoriesAmcModel, AccessoriesAmc.class);
		Session session = em.unwrap(Session.class);
		session.update(accessoriesAmc);
		return accessoriesAmc;
	}

	@SuppressWarnings("deprecation")
	@Transactional
	public List<AccessoriesAmc> getAllAccessoriesAmcs(int limit, int offset) {
		Session session = em.unwrap(Session.class);
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<AccessoriesAmc> criteriaQuery = builder.createQuery(AccessoriesAmc.class);
		Root<AccessoriesAmc> root = criteriaQuery.from(AccessoriesAmc.class);
		criteriaQuery.select(root);
		Query<AccessoriesAmc> query = session.createQuery(criteriaQuery);
		List<AccessoriesAmc> accessoriesAmcs = query.setFirstResult(offset).setMaxResults(limit).getResultList();
		return accessoriesAmcs;
	}

	@Transactional
	@SuppressWarnings({ "deprecation", "unchecked" })
	public List<AccessoriesAmc> getAccessoriesAmcDetail(Long id) {
		Session session = em.unwrap(Session.class);
		Criteria cr = session.createCriteria(AccessoriesAmc.class);
		cr.add(Restrictions.eq("id", id));
		List<AccessoriesAmc> list = (List<AccessoriesAmc>) cr.list();
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

	@Transactional
	@SuppressWarnings({ "deprecation" })
	public List<AccessoriesAmc> searchAccessoriesAmcInfo(String inFindValue) {
		Session session = em.unwrap(Session.class);
		CriteriaBuilder crBuilder = session.getCriteriaBuilder();
		CriteriaQuery<AccessoriesAmc> crq = crBuilder.createQuery(AccessoriesAmc.class);
		Root<AccessoriesAmc> root = crq.from(AccessoriesAmc.class);
		crq.select(root)
				.where(crBuilder.or(crBuilder.like(root.<String>get("name"), "" + inFindValue + "%"),
						crBuilder.like(root.get("mobileNo1"), "" + inFindValue + "%"),
						crBuilder.like(root.<String>get("area"), "" + inFindValue + "%"),
						crBuilder.like(root.<String>get("mobileNo2"), "" + inFindValue + "%"),
						crBuilder.like(root.<String>get("phoneNo1"), "" + inFindValue + "%"),
						crBuilder.like(root.<String>get("phoneNo2"), "" + inFindValue + "%")))
				.orderBy(crBuilder.asc(root.get("name")));

		Query<AccessoriesAmc> q = session.createQuery(crq);
		List<AccessoriesAmc> list = q.getResultList();
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}
}
