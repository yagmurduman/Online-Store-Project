package com.onlineStore.controller;

import com.onlineStore.controller.api.SalesManagerControllerApi;
import com.onlineStore.model.Campaign;
import com.onlineStore.model.Users;
import com.onlineStore.service.SalesManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

@RestController(value="/salesManager")
public class SalesManagerController implements SalesManagerControllerApi {

    @Autowired
    SalesManagerService salesManagerService;

    @Override
    public ResponseEntity updatePriceOfProduct(Integer pId, Float price) {
        salesManagerService.updatePriceOfProduct(pId, price);
        return new ResponseEntity(HttpStatus.OK);
    }

    @Override
    public ResponseEntity updateDiscountOfProduct(Integer pId, Integer discount) {
        salesManagerService.updateDiscountOfProduct(pId, discount);
        return new ResponseEntity(HttpStatus.OK);
    }

    @Override
    public ResponseEntity updateIsDelivered(Integer dId){
        salesManagerService.updateIsDelivered(dId);
        return new ResponseEntity(HttpStatus.OK);
    }


    @Override
    public ResponseEntity<List<Campaign>> getAllCampaigns() {
        List<Campaign> campaigns = salesManagerService.getAllCampaigns();
        if(campaigns == null){
            return new ResponseEntity<>((List<Campaign>) null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(campaigns, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Campaign> insertCampaign(Campaign campaign) {
        salesManagerService.insertCampaign(campaign);
        return new ResponseEntity<> (campaign, HttpStatus.OK);
    }

    @Override
    public ResponseEntity updateIsValidOfCampaign(Integer camId, boolean value) {
        salesManagerService.updateIsValid(camId, value);
        return new ResponseEntity(HttpStatus.OK);
    }

    @Override
    public ResponseEntity deleteCampaignByCamId(Integer camId) {
        salesManagerService.deleteCampaignById(camId);
        return new ResponseEntity(HttpStatus.OK);
    }


}
