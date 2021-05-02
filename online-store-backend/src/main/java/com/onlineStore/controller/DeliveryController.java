package com.onlineStore.controller;


import com.onlineStore.controller.api.DeliveryControllerApi;
import com.onlineStore.model.Delivery;
import com.onlineStore.service.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

@RestController(value="/delivery")
public class DeliveryController implements DeliveryControllerApi {

    @Autowired
    DeliveryService deliveryService;

    @Override
    public ResponseEntity insertProductsFromCartByCid(Integer cId) {
        deliveryService.insertProductsFromCartByCid(cId);
        return new ResponseEntity(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Delivery>> getInvoiceByDid(Integer dId) {
        List<Delivery> deliveries = deliveryService.getInvoiceByDid(dId);
        if (deliveries == null) {
            return new ResponseEntity<>((List<Delivery>) null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(deliveries, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<List<Delivery>>> getInvoiceByDate(Date fromDate,Date toDate) {


        List<List<Delivery>> deliveries = deliveryService.getInvoiceByDate(fromDate, toDate);
        if (deliveries == null) {
            return new ResponseEntity<>((List<List<Delivery>>) null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(deliveries, HttpStatus.OK);

    }

    @Override
    public ResponseEntity<List<List<Delivery>>> getDeliveriesByCid(Integer cId) {
        List<List<Delivery>> deliveries = deliveryService.getDeliveriesByCid(cId);
        if (deliveries == null) {
            return new ResponseEntity<>((List<List<Delivery>>) null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(deliveries, HttpStatus.OK);
    }

    @Override
    public ResponseEntity getInvoicePDF(Integer dId) {

        deliveryService.createPDF(dId);
        return new ResponseEntity(HttpStatus.OK);


    }

    @Override
    public ResponseEntity<List<List<Delivery>>> getAllDeliveries() {
        List<List<Delivery>> deliveries = deliveryService.getAllDeliveries();
        if (deliveries == null) {
            return new ResponseEntity<>((List<List<Delivery>>) null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(deliveries, HttpStatus.OK);
    }
}
