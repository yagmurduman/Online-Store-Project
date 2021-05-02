package com.onlineStore.service;

import com.onlineStore.model.Delivery;

import java.sql.Date;
import java.util.List;

public interface DeliveryService {

    void insertProductsFromCartByCid(Integer cId);

    List<Delivery> getInvoiceByDid(Integer dId);

    List<List<Delivery>> getInvoiceByDate(Date fromDate, Date toDate);

    List<List<Delivery>> getAllDeliveries();

    List<List<Delivery>> getDeliveriesByCid(Integer cId);

    void createPDF(Integer dId);

}
