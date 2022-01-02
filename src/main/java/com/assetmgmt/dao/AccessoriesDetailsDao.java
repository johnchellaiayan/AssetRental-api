package com.assetmgmt.dao;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.assetmgmt.entity.AccessoriesDetails;
import com.assetmgmt.entity.model.AccessoriesDetailsModel;
import com.assetmgmt.repository.AccessoriesDetailsRepository;

@Repository
public class AccessoriesDetailsDao {

	@Autowired
	private EntityManager em;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private AccessoriesDetailsRepository bookingRepository;

	public AccessoriesDetails saveAccessoriesDetails(AccessoriesDetailsModel bookingModel) {
		AccessoriesDetails booking = modelMapper.map(bookingModel, AccessoriesDetails.class);
		bookingRepository.save(booking);
		return booking;
	}

	@Transactional
	public AccessoriesDetails updateAccessoriesDetails(AccessoriesDetailsModel bookingModel, Long id) {
		bookingModel.setId(id);
		AccessoriesDetails booking = modelMapper.map(bookingModel, AccessoriesDetails.class);
		bookingRepository.save(booking);
		return booking;
	}

	public List<AccessoriesDetails> getAllAccessoriesDetailss() {
		return bookingRepository.findAll();
	}

	/*
	 * @Transactional
	 * 
	 * @SuppressWarnings({ "unchecked", "deprecation" }) public
	 * List<AccessoriesDetailsModel> getDatebasedAccessoriesDetailss(String
	 * inFindValue) { List<AccessoriesDetailsModel> bookingModels = new
	 * ArrayList<>(); LocalDateTime currDateTime =
	 * LocalDateTime.now().minusHours(2); LocalDate localDate =
	 * currDateTime.toLocalDate(); Session session = em.unwrap(Session.class);
	 * Criteria cr = session.createCriteria(AccessoriesDetails.class);
	 * cr.add(Restrictions.ilike("reportDate", inFindValue + "%", MatchMode.START));
	 * cr.add(Restrictions.ne("bookStatus", "Completed")); List<AccessoriesDetails>
	 * list = (List<AccessoriesDetails>) cr.list(); if (list != null && list.size()
	 * > 0) { list.forEach(l -> { AccessoriesDetailsModel bookingModel = new
	 * AccessoriesDetailsModel(); bookingModel = modelMapper.map(l,
	 * AccessoriesDetailsModel.class); DateTimeFormatter formatter =
	 * DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"); LocalDateTime dateTime =
	 * LocalDateTime.parse(l.getReportDate(), formatter); LocalDate reportDate =
	 * dateTime.toLocalDate(); if ((dateTime.isAfter(currDateTime) ||
	 * dateTime.isBefore(currDateTime)) && localDate.equals(reportDate) &&
	 * (l.getDriverName() == null || l.getDriverName().isEmpty())) {
	 * bookingModel.setDriverAssigningdTimeExceeded(true); }
	 * bookingModels.add(bookingModel); }); return bookingModels;
	 * 
	 * } return null; }
	 */

	@Transactional
	@SuppressWarnings({ "unchecked", "deprecation" })
	public List<AccessoriesDetailsModel> searchAccessoriesDetailsInfo(String inFindField, String inFindValue) {
		List<AccessoriesDetailsModel> bookingModels = new ArrayList<>();
		LocalDateTime currDateTime = LocalDateTime.now().minusHours(2);
		LocalDate localDate = currDateTime.toLocalDate();
		Session session = em.unwrap(Session.class);
		Criteria cr = session.createCriteria(AccessoriesDetails.class);
		cr.add(Restrictions.ilike(inFindField, "%" + inFindValue + "%", MatchMode.ANYWHERE));
		List<AccessoriesDetails> list = (List<AccessoriesDetails>) cr.list();
		/*
		 * if (list != null && list.size() > 0) { list.forEach(l -> {
		 * AccessoriesDetailsModel bookingModel = new AccessoriesDetailsModel();
		 * bookingModel = modelMapper.map(l, AccessoriesDetailsModel.class);
		 * DateTimeFormatter formatter =
		 * DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"); LocalDateTime dateTime =
		 * LocalDateTime.parse(l.getReportDate(), formatter); LocalDate reportDate =
		 * dateTime.toLocalDate(); if ((dateTime.isAfter(currDateTime) ||
		 * dateTime.isBefore(currDateTime)) && localDate.equals(reportDate) &&
		 * (l.getDriverName() == null || l.getDriverName().isEmpty())) {
		 * bookingModel.setDriverAssigningdTimeExceeded(true); }
		 * bookingModels.add(bookingModel); }); return bookingModels; }
		 */
		return null;
	}

	@Transactional
	@SuppressWarnings({ "deprecation", "unchecked" })
	public AccessoriesDetails getAccessoriesDetailsDetailsById(Long id) {
		Session session = em.unwrap(Session.class);
		Criteria cr = session.createCriteria(AccessoriesDetails.class);
		cr.add(Restrictions.eq("id", id));
		List<AccessoriesDetails> list = (List<AccessoriesDetails>) cr.list();
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	/*
	 * @Transactional public List<AccessoriesDetailsModel>
	 * getActiveAccessoriesDetailss(int limit, int offset) {
	 * List<AccessoriesDetailsModel> bookingModels = new ArrayList<>();
	 * LocalDateTime currDateTime = LocalDateTime.now().minusHours(2); LocalDate
	 * localDate = currDateTime.toLocalDate(); Session session =
	 * em.unwrap(Session.class); String status = "Completed";
	 * List<AccessoriesDetails> bookings = session.createNativeQuery(
	 * "SELECT * FROM booking WHERE DATE(report_date) >= CURDATE() AND book_status not like'%"
	 * + status + "%' order by DATE(report_date) ASC",
	 * AccessoriesDetails.class).setFirstResult(offset).setMaxResults(limit).list();
	 * bookings.forEach(l -> { AccessoriesDetailsModel bookingModel = new
	 * AccessoriesDetailsModel(); bookingModel = modelMapper.map(l,
	 * AccessoriesDetailsModel.class); DateTimeFormatter formatter =
	 * DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"); LocalDateTime dateTime =
	 * LocalDateTime.parse(l.getReportDate(), formatter); LocalDate reportDate =
	 * dateTime.toLocalDate(); if ((dateTime.isAfter(currDateTime) ||
	 * dateTime.isBefore(currDateTime)) && localDate.equals(reportDate) &&
	 * (l.getDriverName() == null || l.getDriverName().isEmpty())) {
	 * bookingModel.setDriverAssigningdTimeExceeded(true); }
	 * bookingModels.add(bookingModel); }); return bookingModels;
	 * 
	 * }
	 */
}
