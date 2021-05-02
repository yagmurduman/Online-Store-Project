package com.onlineStore.controller.api;

import com.onlineStore.model.Campaign;
import com.onlineStore.model.Users;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

public interface SalesManagerControllerApi {

    @PutMapping(value="/salesManager/update-price/pId={pId}&price={price}")
    ResponseEntity updatePriceOfProduct(@PathVariable Integer pId, @PathVariable Float price );

    @PutMapping(value="/salesManager/update-discount/pId={pId}&discount={discount}")
    ResponseEntity updateDiscountOfProduct(@PathVariable Integer pId, @PathVariable Integer discount);

    @PutMapping(value = "/salesManager/update-delivered/dId={dId}")
    ResponseEntity updateIsDelivered (@PathVariable Integer dId);

    @GetMapping(value = "/salesManager/get-all-campaigns")
    ResponseEntity<List<Campaign>> getAllCampaigns();

    @PostMapping(value="/salesManager/insert-campaign")
    ResponseEntity<Campaign> insertCampaign (@RequestBody Campaign campaign);

    @PutMapping(value="/salesManager/update-valid/camId={camId}&value={value}")
    ResponseEntity updateIsValidOfCampaign(@PathVariable Integer camId, @PathVariable boolean value);

    @DeleteMapping(value="/salesManager/delete-campaign={camId}")
    ResponseEntity deleteCampaignByCamId(@PathVariable Integer camId);


}
