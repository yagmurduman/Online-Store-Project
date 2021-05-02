package com.onlineStore.repository;

import com.onlineStore.model.Campaign;
import com.onlineStore.model.Customer;
import com.onlineStore.model.Product;
import com.onlineStore.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.annotation.PostConstruct;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.internet.MimeMultipart;
import javax.sql.DataSource;
import javax.validation.constraints.Null;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;


@Repository
public class SalesManagerRepository extends JdbcDaoSupport {

    @Autowired
    DataSource dataSource;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    DeliveryRepository deliveryRepository;

    @PostConstruct
    private void initiliaze(){
        setDataSource(dataSource);
    }

    public void updatePriceOfProduct(Integer pId, Float price){productRepository.updatePriceOfProduct(pId, price);}

    public void updateDiscountOfProduct(Integer pId, Integer price) {productRepository.updateDiscountOfProduct(pId,price);}

    public List<Users> findByUsername(String userName) {

        String sql = "SELECT * FROM users WHERE userName = '" +  ((userName == null) ? "" : userName)+"'";

        List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);
        List<Users> sms = new ArrayList<Users>();

        for(Map<String, Object> row:rows){

            Users sm =  new Users();
            sm.setuId((Integer)row.get("uId"));
            sm.setType((Integer)row.get("type"));
            sm.setUserName((String)row.get("userName"));
            sm.setPassword((String) row.get("password"));
            sms.add(sm);

        }
        return sms;
    }

    public void insertSM(Users sm){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String sql = "INSERT INTO users " + "(uId, type, userName, password) VALUES (?, ?, ?, ?)";

        getJdbcTemplate().update(sql, new Object[] {sm.getuId(), 2, sm.getUserName(), encoder.encode(sm.getPassword())});
    }

    public void sendEmailWithInvoice(Integer cId, Integer dId) {
        //create invoice by dId
        deliveryRepository.createInvoicePdf(dId);

        //select email from customer
        Customer customer = customerRepository.getCustomerById(cId);

        final String cusMail = customer.getEmail();
        final String cusName = customer.getName() + " " + customer.getSurname();

        final String username = "onlinebookstore308@gmail.com";
        final String password = "bookstore123";

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS

        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("onlinebookstore308@gmail.com"));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(cusMail)
            );
            message.setSubject("Invoice of your purchase");
//            message.setContent("<b>Dear "+ cusName +", </b>"
//                    + "<p> You can find your invoice below. Thank you for your purchase. </p>", "text/html; charset=utf-8" );

            MimeBodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setContent("<b>Dear "+ cusName +", </b>"
                    + "<p> You can find your invoice below. Thank you for your purchase. </p>", "text/html; charset=utf-8" );

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);

            //attachment part -> part 2
            messageBodyPart = new MimeBodyPart();
            String filename = "C:\\Users\\Dell\\Documents\\onlinestore\\online-store-backend\\Invoice_"+dId+".pdf";
            //String filename = "Documents\\onlinestore\\online-store-backend\\Invoice_"+dId+".pdf";
            messageBodyPart.attachFile(filename);
            multipart.addBodyPart(messageBodyPart);

            // Send the complete message parts
            message.setContent(multipart);

            Transport.send(message);

            System.out.println("Invoice email is sent.");

        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateIsDelivered(Integer dId){
        //set isDelivered to true
        String sql = "UPDATE delivery SET isDelivered = ? WHERE dId ='"+dId+"'";
        getJdbcTemplate().update(sql, true);

        String getCId = "SELECT cId FROM delivery WHERE dId = '"+dId+"'";
        List<Map<String, Object>> rows = getJdbcTemplate().queryForList(getCId);
        Integer cId = null;
        for (Map<String, Object> row:rows){
            cId = (Integer) row.get("cId");
        }
        //Integer cId  = getJdbcTemplate().queryForObject(getCId, Integer.class);

        //sendEmail with cId
        if(cId != null)
            sendEmailWithInvoice(cId, dId);

    }

    public List<Campaign> getAllCampaigns(){
        String sql = "SELECT * FROM campaign";

        List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);

        List<Campaign> campaigns = new ArrayList<Campaign>();

        for(Map<String, Object> row:rows){
                Campaign campaign = new Campaign();
                campaign.setCamId((Integer) row.get("camId"));
                campaign.setpId((Integer) row.get("pId"));
                campaign.setDiscount((Integer) row.get("discount"));
                campaign.setCode((Integer) row.get("code"));
                campaign.setValid((Boolean) row.get("isValid"));
                campaign.setLocation((String) row.get("location"));

                campaigns.add(campaign);
        }
        return campaigns;
    }


    public Campaign getCampaignById (Integer camId){
        String sql = "SELECT * FROM campaign WHERE camId = ?";

        return (Campaign) getJdbcTemplate().queryForObject(sql, new Object[]{camId}, new RowMapper<Object>() {
            @Override
            public Object mapRow(ResultSet rs, int i) throws SQLException {
                Campaign campaign = new Campaign();
                campaign.setCamId(rs.getInt("camId"));
                campaign.setpId(rs.getInt("pId"));
                campaign.setDiscount(rs.getInt("discount"));
                campaign.setCode(rs.getInt("code"));
                campaign.setValid(rs.getBoolean("isValid"));
                campaign.setLocation(rs.getString("location"));
                return campaign;
            }
        });
    }

    public void insertCampaign(Campaign campaign){
        Random rand = new Random();
        int upperbound = 999999;
        int code = rand.nextInt(upperbound);
        campaign.setCode(code);

        String sql = "INSERT INTO campaign (pId, discount, code, isValid, location) VALUES (?, ?, ?, ?, ?)";
        getJdbcTemplate().update(sql, new Object[]{campaign.getpId(), campaign.getDiscount(), code, false, campaign.getLocation()});

        //sendEmailForCampaign(campaign);
    }

    public void updateIsValid(Integer camId, boolean value){
        String sql = "UPDATE campaign SET isValid = ? WHERE camId =?";
        getJdbcTemplate().update(sql, value, camId);

        Campaign campaign = getCampaignById(camId);
        //if the campaign is valid, send email about discount to all members or members in a specific location
        if(value) {
            sendEmailForCampaign(campaign);
        }

    }

    public void deleteCampaignById(Integer camId){
        String sql = "DELETE FROM campaign WHERE camId = ?";
        getJdbcTemplate().update(sql, camId);
    }

    public void sendEmailForCampaign(Campaign campaign){
        String addresses = customerRepository.getEmailsByLocation(campaign.getLocation());

        if (addresses == "") {
            return;
        }
        Product product = productRepository.getProductById(campaign.getpId());

        final String username = "onlinebookstore308@gmail.com";
        final String password = "bookstore123";

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS

        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("onlinebookstore308@gmail.com"));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(addresses)
            );
            message.setSubject("Discount Alert at Book Store!");
            message.setContent("Dear member of Book Store Family ,"
                    + "<p>Discount has started for <b>"+ product.getpName() + "</b> with <b>"+ campaign.getDiscount()+"%</b> discount. Hurry up, " +
                    "don't miss! Discount code is "+ campaign.getCode() +" </p>", "text/html; charset=utf-8" );

            Transport.send(message);

            System.out.println("Campaign email is sent.");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public List<Users> getAllSM(){

        String sql = "SELECT * FROM users WHERE type = 2";
        List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);
        List<Users> users = new ArrayList<Users>();

        for(Map<String, Object> row:rows){

            Users user =  new Users();
            user.setuId((Integer)row.get("uId"));
            user.setType((Integer)row.get("type"));
            user.setUserName((String)row.get("userName"));
            user.setPassword((String) row.get("password"));
            users.add(user);

        }
        return users;

    }

}
