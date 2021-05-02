package com.onlineStore.service.Imp;

import com.onlineStore.model.Campaign;
import com.onlineStore.model.Users;
import com.onlineStore.repository.SalesManagerRepository;
import com.onlineStore.repository.UsersRepository;
import com.onlineStore.service.SalesManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

@Service
public class SalesManagerImp implements SalesManagerService {

    @Autowired
    SalesManagerRepository salesManagerRepository;

    @Autowired
    UsersRepository usersRepository;

    @Override
    public void updatePriceOfProduct(Integer pId, Float price) {
        salesManagerRepository.updatePriceOfProduct(pId, price);
    }

    @Override
    public void updateDiscountOfProduct(Integer pId, Integer price) {
        salesManagerRepository.updateDiscountOfProduct(pId, price);
    }

    @Override
    public void updateIsDelivered(Integer dId){
        salesManagerRepository.updateIsDelivered(dId);
    }

    public Integer registerSM(Users sm) {

        List<Users> list = usersRepository.findByUsername(sm.getUserName());

        if (list.size() > 0) {
            return -1;
        }

        salesManagerRepository.insertSM(sm); ;
        return 1;

    }

    @Override
    public List<Campaign> getAllCampaigns() {
        return salesManagerRepository.getAllCampaigns();
    }

    @Override
    public void insertCampaign(Campaign campaign) {
        salesManagerRepository.insertCampaign(campaign);
    }

    @Override
    public void updateIsValid(Integer camId, boolean value) {
        Campaign campaign = salesManagerRepository.getCampaignById(camId);
        if (campaign != null)
            salesManagerRepository.updateIsValid(camId, value);
    }

    @Override
    public void deleteCampaignById(Integer camId) {
        Campaign campaign = salesManagerRepository.getCampaignById(camId);
        if(campaign != null){
            salesManagerRepository.deleteCampaignById(camId);
        }
    }

    @Override
    public List<Users> getAllSM() {
        return salesManagerRepository.getAllSM();
    }
}
