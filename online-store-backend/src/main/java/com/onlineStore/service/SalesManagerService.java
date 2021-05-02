package com.onlineStore.service;

import com.onlineStore.model.Campaign;
import com.onlineStore.model.Users;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

public interface SalesManagerService {

    void updatePriceOfProduct(Integer pId, Float price);
    void updateDiscountOfProduct(Integer pId, Integer price);
    Integer registerSM(Users sm);
    void updateIsDelivered (Integer dId);

    List<Campaign> getAllCampaigns();
    void insertCampaign (Campaign campaign);
    void updateIsValid (Integer camId, boolean value);
    void deleteCampaignById (Integer camId);
    List<Users> getAllSM ();


}
