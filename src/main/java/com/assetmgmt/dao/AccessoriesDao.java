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

import com.assetmgmt.entity.Accessories;
import com.assetmgmt.entity.model.AccessoriesModel;
import com.assetmgmt.repository.AccessoriesRepository;

@Repository
public class AccessoriesDao {

	@Autowired
	private EntityManager em;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private AccessoriesRepository accessoriesRepository;

	public Accessories saveAccessories(AccessoriesModel accessoriesModel) {
		Accessories accessories = modelMapper.map(accessoriesModel, Accessories.class);
		accessoriesRepository.save(accessories);
		return accessories;
	}

	@Transactional
	public Accessories updateAccessories(AccessoriesModel accessoriesModel, Long id) {
		accessoriesModel.setId(id.toString());
		Accessories accessories = modelMapper.map(accessoriesModel, Accessories.class);
		Session session = em.unwrap(Session.class);
		session.update(accessories);
		return accessories;
	}

	@SuppressWarnings("deprecation")
	@Transactional
	public List<Accessories> getAllAccessoriess(int limit, int offset) {
		Session session = em.unwrap(Session.class);
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Accessories> criteriaQuery = builder.createQuery(Accessories.class);
		Root<Accessories> root = criteriaQuery.from(Accessories.class);
		criteriaQuery.select(root);
		Query<Accessories> query = session.createQuery(criteriaQuery);
		List<Accessories> accessoriess = query.setFirstResult(offset).setMaxResults(limit).getResultList();
		return accessoriess;
	}

	@Transactional
	@SuppressWarnings({ "deprecation", "unchecked" })
	public List<Accessories> getAccessoriesDetail(Long id) {
		Session session = em.unwrap(Session.class);
		Criteria cr = session.createCriteria(Accessories.class);
		cr.add(Restrictions.eq("id", id));
		List<Accessories> list = (List<Accessories>) cr.list();
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

	@Transactional
	@SuppressWarnings({ "deprecation" })
	public List<Accessories> searchAccessoriesInfo(String inFindValue) {
		Session session = em.unwrap(Session.class);
		CriteriaBuilder crBuilder = session.getCriteriaBuilder();
		CriteriaQuery<Accessories> crq = crBuilder.createQuery(Accessories.class);
		Root<Accessories> root = crq.from(Accessories.class);
		crq.select(root)
				.where(crBuilder.or(crBuilder.like(root.<String>get("name"), "" + inFindValue + "%"),
						crBuilder.like(root.get("mobileNo1"), "" + inFindValue + "%"),
						crBuilder.like(root.<String>get("area"), "" + inFindValue + "%"),
						crBuilder.like(root.<String>get("mobileNo2"), "" + inFindValue + "%"),
						crBuilder.like(root.<String>get("phoneNo1"), "" + inFindValue + "%"),
						crBuilder.like(root.<String>get("phoneNo2"), "" + inFindValue + "%")))
				.orderBy(crBuilder.asc(root.get("name")));

		Query<Accessories> q = session.createQuery(crq);
		List<Accessories> list = q.getResultList();
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}
}
