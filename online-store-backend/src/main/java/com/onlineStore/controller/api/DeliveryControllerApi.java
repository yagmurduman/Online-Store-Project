package com.onlineStore.controller.api;

import com.onlineStore.model.Delivery;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.sql.Date;
import java.util.List;

public interface DeliveryControllerApi {

    @PostMapping(value="/delivery/cartCheckout={cId}")
    ResponseEntity insertProductsFromCartByCid(@PathVariable Integer cId);

    @GetMapping(value="/delivery/get-invoice-by-dId")
    ResponseEntity<List<Delivery>> getInvoiceByDid(Integer dId);

    @GetMapping(value="/delivery/get-invoice-by-date")
    ResponseEntity<List<List<Delivery>>> getInvoiceByDate (Date fromDate, Date toDate);

    @GetMapping(value="/delivery/get-deliveries-by-cId={cId}")
    ResponseEntity<List<List<Delivery>>> getDeliveriesByCid (@PathVariable Integer cId);

    @GetMapping(value="/delivery/get-invoice-pdf")
    ResponseEntity getInvoicePDF (Integer dId);

    @GetMapping(value="/delivery/get-all-deliveries")
    ResponseEntity<List<List<Delivery>>> getAllDeliveries ();

}
