package com.onlineStore.repository;

import com.onlineStore.model.Customer;
import com.onlineStore.model.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository
public class ReviewRepository extends JdbcDaoSupport {

    @Autowired
    DataSource dataSource;

    @PostConstruct
    private void initiliaze(){
        setDataSource(dataSource);
    }

    @Autowired
    CustomerRepository customerRepository;

    public List<Review> getAllReviewsToApprove (){
        String sql = "SELECT * FROM review";
        List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);

        List<Review> reviews = new ArrayList<Review>();

        for(Map<String, Object> row:rows){
            if((Boolean)row.get("isApproved") == false){
                Review review = new Review();
                review.setcId((Integer)row.get("cId"));
                review.setpId((Integer) row.get("pId"));
                review.setcName((String) row.get("cName"));
                review.setRate((Integer) row.get("rate"));
                review.setComment((String) row.get("comment"));
                review.setrDate((Date)row.get("rDate"));
                review.setApproved((Boolean) row.get("isApproved"));

                reviews.add(review);
            }

        }
        return reviews;
    }

    public void addReviewToProduct (Review review){
        String sql = "INSERT INTO review " + "(cId, pId, cName, rate, comment, rDate, isApproved) VALUES (?, ?, ?, ?, ?, ?, ?)";

        Customer cus = customerRepository.getCustomerById(review.getcId());
        String cusName = cus.getName() + " " + cus.getSurname();

        long millis=System.currentTimeMillis();
        java.sql.Date date=new java.sql.Date(millis);

        getJdbcTemplate().update(sql, new Object[]{review.getcId(), review.getpId(), cusName, review.getRate(), review.getComment(), date, false });
    }

    public void deleteReviewById (Integer cId, Integer pId){
        String sql = "DELETE FROM review WHERE cId = ? AND pId = ?";
        getJdbcTemplate().update(sql, cId, pId);
    }

    public List<Review> getReviewsByPid(Integer pId){
        String sql = "SELECT * FROM review WHERE pId = ?";
        List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql, pId);
        List<Review> reviews = new ArrayList<Review>();

        for(Map<String, Object> row:rows){
            if((Boolean)row.get("isApproved") == true){
                Review review = new Review();
                review.setcId((Integer)row.get("cId"));
                review.setpId((Integer) row.get("pId"));
                review.setcName((String) row.get("cName"));
                review.setRate((Integer) row.get("rate"));
                review.setComment((String) row.get("comment"));
                review.setrDate((Date)row.get("rDate"));
                review.setApproved((Boolean) row.get("isApproved"));

                reviews.add(review);
            }

        }
        return reviews;
    }

    public Float getAvgRateOfProductById(Integer pId) {
        String sql = "SELECT AVG(rate) FROM review WHERE isApproved = 1 AND pId = " + pId;
        Float avgRate = getJdbcTemplate().queryForObject(sql, new Object[] {}, Float.class);

        if(avgRate == null)
            avgRate = Float.valueOf(0);

        return avgRate;
    }

    public void updateIsApproved(Integer cId, Integer pId, Boolean approval){
        String sql = "UPDATE review SET isApproved = ? WHERE cId = ? AND pId =?";
        getJdbcTemplate().update(sql, approval, cId, pId);
    }
}
