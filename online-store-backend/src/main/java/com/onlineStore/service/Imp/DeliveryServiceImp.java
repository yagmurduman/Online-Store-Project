package com.onlineStore.service.Imp;

import com.onlineStore.model.Delivery;
import com.onlineStore.repository.DeliveryRepository;
import com.onlineStore.service.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class DeliveryServiceImp implements DeliveryService {

    @Autowired
    DeliveryRepository deliveryRepository;

    @Override
    public void insertProductsFromCartByCid(Integer cId) {
        deliveryRepository.insertProductsFromCartByCid(cId);
    }

    @Override
    public List<Delivery> getInvoiceByDid(Integer dId) {
        return deliveryRepository.getDeliveryByDid(dId);
    }

    @Override
    public List<List<Delivery>> getInvoiceByDate(Date fromDate, Date toDate) {
        return deliveryRepository.getDeliveryByDate(fromDate,toDate);
    }

    @Override
    public List<List<Delivery>> getDeliveriesByCid(Integer cId) {
        return deliveryRepository.getDeliveriesByCid(cId);
    }

    @Override
    public void createPDF(Integer dId) {
        deliveryRepository.createInvoicePdf(dId);
    }

    @Override
    public List<List<Delivery>> getAllDeliveries() {
        return deliveryRepository.getAllDeliveries();
    }
}
