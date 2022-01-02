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

import com.assetmgmt.entity.MasterLessor;
import com.assetmgmt.entity.model.LessorModel;
import com.assetmgmt.repository.LessorRepository;

@Repository
public class LessorDao {

	@Autowired
	private EntityManager em;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private LessorRepository lessorRepository;

	public MasterLessor saveLessor(LessorModel lessorModel) {
		MasterLessor lessor = modelMapper.map(lessorModel, MasterLessor.class);
		lessorRepository.save(lessor);
		return lessor;
	}

	@Transactional
	public MasterLessor updateLessor(LessorModel lessorModel, Long id) {
		lessorModel.setId(id.toString());
		MasterLessor lessor = modelMapper.map(lessorModel, MasterLessor.class);
		Session session = em.unwrap(Session.class);
		session.update(lessor);
		return lessor;
	}

	@SuppressWarnings("deprecation")
	@Transactional
	public List<MasterLessor> getAllLessors(int limit, int offset) {
		Session session = em.unwrap(Session.class);
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<MasterLessor> criteriaQuery = builder.createQuery(MasterLessor.class);
		Root<MasterLessor> root = criteriaQuery.from(MasterLessor.class);
		criteriaQuery.select(root);
		Query<MasterLessor> query = session.createQuery(criteriaQuery);
		List<MasterLessor> lessors = query.setFirstResult(offset).setMaxResults(limit).getResultList();
		return lessors;
	}

	@Transactional
	@SuppressWarnings({ "deprecation", "unchecked" })
	public List<MasterLessor> getLessorDetail(Long id) {
		Session session = em.unwrap(Session.class);
		Criteria cr = session.createCriteria(MasterLessor.class);
		cr.add(Restrictions.eq("id", id));
		List<MasterLessor> list = (List<MasterLessor>) cr.list();
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

	@Transactional
	@SuppressWarnings({ "deprecation" })
	public List<MasterLessor> searchLessorInfo(String inFindValue) {
		Session session = em.unwrap(Session.class);
		CriteriaBuilder crBuilder = session.getCriteriaBuilder();
		CriteriaQuery<MasterLessor> crq = crBuilder.createQuery(MasterLessor.class);
		Root<MasterLessor> root = crq.from(MasterLessor.class);
		crq.select(root)
				.where(crBuilder.or(crBuilder.like(root.<String>get("name"), "" + inFindValue + "%"),
						crBuilder.like(root.get("mobileNo1"), "" + inFindValue + "%"),
						crBuilder.like(root.<String>get("area"), "" + inFindValue + "%"),
						crBuilder.like(root.<String>get("mobileNo2"), "" + inFindValue + "%"),
						crBuilder.like(root.<String>get("phoneNo1"), "" + inFindValue + "%"),
						crBuilder.like(root.<String>get("phoneNo2"), "" + inFindValue + "%")))
				.orderBy(crBuilder.asc(root.get("name")));

		Query<MasterLessor> q = session.createQuery(crq);
		List<MasterLessor> list = q.getResultList();
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}
}
