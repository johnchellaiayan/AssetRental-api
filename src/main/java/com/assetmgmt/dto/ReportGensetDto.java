package com.assetmgmt.dto;


public interface ReportGensetDto {

    // td.diesel_id as dieselId, td.base_amount as baseAmount, td.cgst, td.sgst, " +
    //			"td.start_date as startDate, td.end_date as endDate,  " +
    //			" td.description  from rental_agreements ra "
    String getDieselId();

    String getDescription();

    String getCgst();

    String getSgst();

    String getStartDate();

    String getEndDate();

    String getBaseAmount();

}
