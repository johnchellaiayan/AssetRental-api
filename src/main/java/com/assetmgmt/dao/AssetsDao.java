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

import com.assetmgmt.entity.MasterAssets;
import com.assetmgmt.entity.model.AssetsModel;
import com.assetmgmt.repository.AssetsRepository;

@Repository
public class AssetsDao {

	@Autowired
	private EntityManager em;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private AssetsRepository assetsRepository;

	public MasterAssets saveAssets(AssetsModel assetsModel) {
		MasterAssets assets = modelMapper.map(assetsModel, MasterAssets.class);
		assetsRepository.save(assets);
		return assets;
	}

	@Transactional
	public MasterAssets updateAssets(AssetsModel assetsModel, Long id) {
		assetsModel.setId(id.toString());
		MasterAssets assets = modelMapper.map(assetsModel, MasterAssets.class);
		Session session = em.unwrap(Session.class);
		session.update(assets);
		return assets;
	}

	@SuppressWarnings("deprecation")
	@Transactional
	public List<MasterAssets> getAllAssets(int limit, int offset) {
		Session session = em.unwrap(Session.class);
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<MasterAssets> criteriaQuery = builder.createQuery(MasterAssets.class);
		Root<MasterAssets> root = criteriaQuery.from(MasterAssets.class);
		criteriaQuery.select(root);
		Query<MasterAssets> query = session.createQuery(criteriaQuery);
		List<MasterAssets> assetss = query.setFirstResult(offset).setMaxResults(limit).getResultList();
		return assetss;
	}

	@Transactional
	@SuppressWarnings({ "deprecation", "unchecked" })
	public List<MasterAssets> getAssetsDetail(Long id) {
		Session session = em.unwrap(Session.class);
		Criteria cr = session.createCriteria(MasterAssets.class);
		cr.add(Restrictions.eq("id", id));
		List<MasterAssets> list = (List<MasterAssets>) cr.list();
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

	@Transactional
	@SuppressWarnings({ "deprecation" })
	public List<MasterAssets> searchAssetsInfo(String inFindValue) {
		Session session = em.unwrap(Session.class);
		CriteriaBuilder crBuilder = session.getCriteriaBuilder();
		CriteriaQuery<MasterAssets> crq = crBuilder.createQuery(MasterAssets.class);
		Root<MasterAssets> root = crq.from(MasterAssets.class);
		crq.select(root)
				.where(crBuilder.or(crBuilder.like(root.<String>get("buildingName"), "" + inFindValue + "%"),
						crBuilder.like(root.get("mobileNo1"), "" + inFindValue + "%"),
						crBuilder.like(root.<String>get("area"), "" + inFindValue + "%"),
						crBuilder.like(root.<String>get("mobileNo2"), "" + inFindValue + "%"),
						crBuilder.like(root.<String>get("phoneNo1"), "" + inFindValue + "%"),
						crBuilder.like(root.<String>get("phoneNo2"), "" + inFindValue + "%")))
				.orderBy(crBuilder.asc(root.get("name")));

		Query<MasterAssets> q = session.createQuery(crq);
		List<MasterAssets> list = q.getResultList();
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}
}
