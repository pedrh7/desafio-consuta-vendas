package com.devsuperior.dsmeta.dto;

import java.time.LocalDate;

import com.devsuperior.dsmeta.entities.Sale;

public class SaleReportDTO {

    private Long id;
    private LocalDate date;
    private Integer deals;
    private Double amount;
    private String sellerName;

    public SaleReportDTO(Sale entity) {
        id = entity.getId();
        date = entity.getDate();
        deals = entity.getDeals();
        amount = entity.getAmount();
        sellerName = entity.getSeller().getName();
    }
}