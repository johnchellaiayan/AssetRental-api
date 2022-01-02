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

import com.assetmgmt.entity.MasterLessee;
import com.assetmgmt.entity.model.LesseeModel;
import com.assetmgmt.repository.LesseeRepository;

@Repository
public class LesseeDao {

	@Autowired
	private EntityManager em;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private LesseeRepository lesseeRepository;

	public MasterLessee saveLessee(LesseeModel lesseeModel) {
		MasterLessee lessee = modelMapper.map(lesseeModel, MasterLessee.class);
		lesseeRepository.save(lessee);
		return lessee;
	}

	@Transactional
	public MasterLessee updateLessee(LesseeModel lesseeModel, Long id) {
		lesseeModel.setId(id.toString());
		MasterLessee lessee = modelMapper.map(lesseeModel, MasterLessee.class);
		Session session = em.unwrap(Session.class);
		session.update(lessee);
		return lessee;
	}

	@SuppressWarnings("deprecation")
	@Transactional
	public List<MasterLessee> getAllLessees(int limit, int offset) {
		Session session = em.unwrap(Session.class);
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<MasterLessee> criteriaQuery = builder.createQuery(MasterLessee.class);
		Root<MasterLessee> root = criteriaQuery.from(MasterLessee.class);
		criteriaQuery.select(root);
		Query<MasterLessee> query = session.createQuery(criteriaQuery);
		List<MasterLessee> lessees = query.setFirstResult(offset).setMaxResults(limit).getResultList();
		return lessees;
	}

	@Transactional
	@SuppressWarnings({ "deprecation", "unchecked" })
	public List<MasterLessee> getLesseeDetail(Long id) {
		Session session = em.unwrap(Session.class);
		Criteria cr = session.createCriteria(MasterLessee.class);
		cr.add(Restrictions.eq("id", id));
		List<MasterLessee> list = (List<MasterLessee>) cr.list();
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

	@Transactional
	@SuppressWarnings({ "deprecation" })
	public List<MasterLessee> searchLesseeInfo(String inFindValue) {
		Session session = em.unwrap(Session.class);
		CriteriaBuilder crBuilder = session.getCriteriaBuilder();
		CriteriaQuery<MasterLessee> crq = crBuilder.createQuery(MasterLessee.class);
		Root<MasterLessee> root = crq.from(MasterLessee.class);
		crq.select(root)
				.where(crBuilder.or(crBuilder.like(root.<String>get("name"), "" + inFindValue + "%"),
						crBuilder.like(root.get("mobileNo1"), "" + inFindValue + "%"),
						crBuilder.like(root.<String>get("area"), "" + inFindValue + "%"),
						crBuilder.like(root.<String>get("mobileNo2"), "" + inFindValue + "%"),
						crBuilder.like(root.<String>get("phoneNo1"), "" + inFindValue + "%"),
						crBuilder.like(root.<String>get("phoneNo2"), "" + inFindValue + "%")))
				.orderBy(crBuilder.asc(root.get("name")));

		Query<MasterLessee> q = session.createQuery(crq);
		List<MasterLessee> list = q.getResultList();
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}
}
