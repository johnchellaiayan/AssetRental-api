package com.assetmgmt.dto;


public interface ReportDieselDto {

    // td.diesel_id, td.base_amount as baseAmount, td.cgst, td.sgst, td.start_date, td.end_date,  " +
    //			" td.description
    String getDieselId();

    String getDescription();

    String getCgst();

    String getSgst();

    String getStartDate();

    String getEndDate();

    String getBaseAmount();

}
