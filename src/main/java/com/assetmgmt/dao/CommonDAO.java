package com.assetmgmt.dao;

import java.time.Duration;
import java.util.*;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import com.assetmgmt.dto.ReportAcamcDto;
import com.assetmgmt.entity.MasterLessee;
import com.assetmgmt.entity.MasterLessor;
import com.assetmgmt.entity.model.ReportModel;
import com.assetmgmt.repository.*;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.assetmgmt.dto.MessageDto;
import com.assetmgmt.dto.StatisticsDto;
import com.assetmgmt.entity.model.MessageModel;

@Repository
public class CommonDAO {

    @Autowired
    EntityManager entityManager;

    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    private LesseeRepository lesseeRepositoryy;

    @Autowired
    private LessorRepository lessorRepository;

    @Autowired
    private BankDetailsRepository bankDetailsRepository;

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Transactional
    public StatisticsDto getStatistics() {
        Session sess = entityManager.unwrap(Session.class);
        Date currentDate = new Date();
        Date thirtyDaysFromCurrentDate = new Date(currentDate.getTime() + Duration.ofDays(30).toMillis());
        String sql = "SELECT"
                + "  (SELECT COUNT(*) FROM driver ) AS totalDrivers,"
                + "  (SELECT COUNT(*) FROM customer) AS totalCustomers,"
                + "  (SELECT COUNT(*) FROM booking) AS totalCurrentBookings,"
                + "  (SELECT COUNT(*) FROM user) AS totalUsers,"
                + "  (SELECT COUNT(*) FROM driver where license_expiry_date<=:reqDate OR license_expiry_date<=CURDATE() ) AS expiredLiscenseDrivers";


        Query q = sess.createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(StatisticsDto.class));
        q.setParameter("reqDate", thirtyDaysFromCurrentDate);
        List<StatisticsDto> dto = (List<StatisticsDto>) q.list();
        return dto.get(0);
    }

    @Transactional
    public MessageDto sedMessageTo(MessageModel messageModel) {
        MessageDto mtdo = new MessageDto();
        mtdo.setCustPhone(messageModel.getCustphone());
        mtdo.setSmsto(messageModel.getSmsto());
        return mtdo;
    }

    @Transactional
    public Map<String, Object> getReportDetails(ReportModel reportModel) {
        Session sess = entityManager.unwrap(Session.class);

        Optional<MasterLessor> masterLessor = lessorRepository.findById(1L);

        Optional<MasterLessee> masterLessee = lesseeRepositoryy.findById(1L);
        List reportDtos = new ArrayList();
        String transDetailsStr = "billDetail";

        switch (reportModel.getReporttype()) {
            case "diesel":
                reportDtos = reportRepository.findDieselTransReport(
                        reportModel.getLessorid(), reportModel.getLesseeid(), reportModel.getStartdate(), reportModel.getEnddate());
                break;
            case "acamc":
                reportDtos = reportRepository.findAmcTransReport(
                        reportModel.getLessorid(), reportModel.getLesseeid(), reportModel.getStartdate(), reportModel.getEnddate(), "1");
                transDetailsStr = "amcdetails";
                break;

            case "genset":
                reportDtos = reportRepository.findAmcTransReport(
                        reportModel.getLessorid(), reportModel.getLesseeid(), reportModel.getStartdate(), reportModel.getEnddate(), "2");
                transDetailsStr = "amcdetails";
                break;

            case "liftamc":
                reportDtos = reportRepository.findAmcTransReport(
                        reportModel.getLessorid(), reportModel.getLesseeid(), reportModel.getStartdate(), reportModel.getEnddate(), "3");
                transDetailsStr = "amcdetails";
                break;

            case "rental":
                reportDtos = reportRepository.findRentalAgreementReport(
                        reportModel.getLessorid(), reportModel.getLesseeid(), reportModel.getStartdate(), reportModel.getEnddate());
                transDetailsStr = "rentdetails";
                break;
            default:
                 masterLessor = Optional.ofNullable(null);
                 masterLessee = Optional.ofNullable(null);
        }

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("lessorDetail", masterLessor);
        map.put("lesseeDetail", masterLessee);
        map.put(transDetailsStr, reportDtos);
        if (reportModel.getReporttype().equalsIgnoreCase("acamc")){
            Map acTotalAmount = findAcTotalAmount(reportDtos);
            map.put("totalamount", acTotalAmount);
        }
        return map;
    }

    Map<String, Float> findAcTotalAmount(List<ReportAcamcDto> reportDtos){
        Map<String, Float>  rettotal = new HashMap<String, Float>();
        Float totalBaseamount = new Float("0.0");
        Float totalCgstamount =  new Float("0.0");
        Float totalsgstamount =  new Float("0.0");
        Float totalamount =  new Float("0.0");
        for(ReportAcamcDto acamt: reportDtos) {
            totalBaseamount +=  Float.parseFloat(acamt.getBaseAmount());
            totalCgstamount +=  Float.parseFloat(acamt.getBaseAmount()) *  (Float.parseFloat(acamt.getCgst())/100);
            totalsgstamount +=  Float.parseFloat(acamt.getBaseAmount()) *  (Float.parseFloat(acamt.getSgst())/100);
            totalamount += totalBaseamount + totalCgstamount + totalsgstamount;
        }
        rettotal.put("basicPrice", totalBaseamount);
        rettotal.put("CGST", totalCgstamount);
        rettotal.put("SGST", totalsgstamount);
        rettotal.put("totalAmcCost", totalamount);

        return  rettotal;

    }
}


//    //simpleExample
//    Query query = em.createNativeQuery(
//"SELECT u.name,s.something FROM user u,  someTable s WHERE s.user_id = u.id",
//NameSomething.class);
//    List list = (List<NameSomething.class>) query.getResultList();
//Optional<BankDetails> bankDetails = bankDetailsRepository.findById(masterLessor.get().getBankDetails().getId());
