package com.onlineStore.repository;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.onlineStore.model.Delivery;
import com.onlineStore.model.Product;
import com.onlineStore.model.ShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class DeliveryRepository extends JdbcDaoSupport {

    @Autowired
    DataSource dataSource;

    @Autowired
    ShoppingCartRepository cartRepository;

    @Autowired
    ProductRepository productRepository;

    @PostConstruct
    private void initiliaze(){
        setDataSource(dataSource);
    }

    public List<List<Delivery>> getAllDeliveries(){
        String sql = "SELECT  * FROM delivery";
        List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);

        List<List<Delivery>> deliveries = new ArrayList<>();
        Integer dId = 0;
        List<Delivery> delPerDid = null;
        for (Map<String, Object> row:rows){
            Integer rowDid = (Integer) row.get("dId");
            if(!dId.equals(rowDid)){
                if(delPerDid != null){ deliveries.add(delPerDid);}
                dId = rowDid;
                delPerDid = new ArrayList<>();
            }
            Delivery delivery = new Delivery();
            delivery.setcId((Integer)row.get("cId"));
            delivery.setdId((Integer)row.get("dId"));
            delivery.setpId((Integer)row.get("pId"));
            delivery.setpName((String) row.get("pName"));
            delivery.setDate((Date)row.get("dDate"));
            delivery.setQuantity((int)row.get("quantity"));
            delivery.setPrice((Float) row.get("price"));
            delivery.setDelivered((Boolean) row.get("isDelivered"));
            delPerDid.add(delivery);
        }

        if(delPerDid != null){ deliveries.add(delPerDid);}

        return deliveries;
    }


    public void insertProductsFromCartByCid(Integer cId){

        List<ShoppingCart> carts = cartRepository.getProductsByCustomerId(cId);

        String sqlID = "SELECT MAX(dId) FROM delivery";
        Integer dId = getJdbcTemplate().queryForObject(sqlID, new Object[] {}, Integer.class);

        if(dId != null) {dId += 1;}
        else {dId = 1;}

        String sql = "INSERT INTO delivery " + "(dId, cId, pId, quantity,dDate, price, isDelivered, pName) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        Integer finalDId = dId;
        getJdbcTemplate().batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                Delivery delivery = new Delivery();
                ShoppingCart cart = carts.get(i);
                String proName = productRepository.getProductById(cart.getpId()).getpName();
                ps.setInt(1, finalDId);
                ps.setInt(2,cart.getcId());
                ps.setInt(3,cart.getpId());
                ps.setInt(4,cart.getQuantity());
                long millis=System.currentTimeMillis();
                java.sql.Date date=new java.sql.Date(millis);
                ps.setDate(5,date);
                ps.setFloat(6, cart.getPrice());
                ps.setBoolean(7, false);
                ps.setString(8, proName );

                int newStock = productRepository.getProductById(cart.getpId()).getStock() - cart.getQuantity();
                productRepository.updateStockofProduct(cart.getpId(),newStock);
            }

            @Override
            public int getBatchSize() {
                return carts.size();
            }
        });

        cartRepository.deleteProductsByCusId(cId);

    }

    public List<Delivery> getDeliveryByDid(Integer dId){

        String sql = "SELECT * FROM delivery WHERE dId = " + dId;


        List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);
        List<Delivery> deliveries = new ArrayList<Delivery>();

        for(Map<String, Object> row:rows){
            Delivery delivery = new Delivery();
            delivery.setcId((Integer)row.get("cId"));
            delivery.setdId((Integer)row.get("dId"));
            delivery.setpId((Integer)row.get("pId"));
            delivery.setpName((String) row.get("pName"));
            delivery.setDate((Date)row.get("dDate"));
            delivery.setQuantity((int)row.get("quantity"));
            delivery.setPrice((Float) row.get("price"));
            delivery.setDelivered((Boolean)row.get("isDelivered"));
            deliveries.add(delivery);
        }
        return deliveries;

    }

    public List<List<Delivery>> getDeliveryByDate(Date fromDate, Date toDate){

        String sql = "SELECT * from delivery where dDate >= '" + fromDate + "' AND dDate <= '" +toDate+"'";


        List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);
        List<List<Delivery>> deliveries = new ArrayList<>();
        Integer dId = 0;
        List<Delivery> delPerDid = null;
        for (Map<String, Object> row:rows){
            Integer rowDid = (Integer) row.get("dId");
            if(!dId.equals(rowDid)){
                if(delPerDid != null){ deliveries.add(delPerDid);}
                dId = rowDid;
                delPerDid = new ArrayList<>();
            }
            Delivery delivery = new Delivery();
            delivery.setcId((Integer)row.get("cId"));
            delivery.setdId((Integer)row.get("dId"));
            delivery.setpId((Integer)row.get("pId"));
            delivery.setpName((String) row.get("pName"));
            delivery.setDate((Date)row.get("dDate"));
            delivery.setQuantity((int)row.get("quantity"));
            delivery.setPrice((Float) row.get("price"));
            delivery.setDelivered((Boolean) row.get("isDelivered"));
            delPerDid.add(delivery);
        }

        if(delPerDid != null){ deliveries.add(delPerDid);}

        return deliveries;

    }

    public List<List<Delivery>> getDeliveriesByCid(Integer cId){
        String sql = "SELECT * FROM delivery WHERE cId ='"+ cId +"'";
        List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);

        List<List<Delivery>> deliveries = new ArrayList<>();
        Integer dId = 0;
        List<Delivery> delPerDid = null;
        for (Map<String, Object> row:rows){
            Integer rowDid = (Integer) row.get("dId");
            if(!dId.equals(rowDid)){
                if(delPerDid != null){ deliveries.add(delPerDid);}
                dId = rowDid;
                delPerDid = new ArrayList<>();
            }
            Delivery delivery = new Delivery();
            delivery.setcId((Integer)row.get("cId"));
            delivery.setdId((Integer)row.get("dId"));
            delivery.setpId((Integer)row.get("pId"));
            delivery.setpName((String) row.get("pName"));
            delivery.setDate((Date)row.get("dDate"));
            delivery.setQuantity((int)row.get("quantity"));
            delivery.setPrice((Float) row.get("price"));
            delivery.setDelivered((Boolean) row.get("isDelivered"));
            delPerDid.add(delivery);
        }

        if(delPerDid != null){ deliveries.add(delPerDid);}

        return deliveries;
    }


    public void createInvoicePdf(Integer dId){
        try {
            String dest = "Invoice_"+dId+".pdf";
            PdfWriter writer = new PdfWriter(dest);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);
            Paragraph title = new Paragraph("Invoice For Delivery : " + dId);
            document.add(title);
            float [] pointColumnWidths = {100F,100F,100F,100F,100F, 100F};
            Table table = new Table(pointColumnWidths);
            table.addCell("DATE");
            table.addCell("Product Id");
            table.addCell("Name");
            table.addCell("Price");
            table.addCell("Quantity");
            table.addCell("Total Price");

            List<Delivery> deliveries = getDeliveryByDid(dId);
            for (Delivery del : deliveries) {
                Date date = del.getDate();
                Integer pId = del.getpId();
                Product pro = productRepository.getProductById(pId);
                String pName = pro.getpName();
                Float price  = pro.getSellingPrice();
                Integer quantity = del.getQuantity();
                Float tPrice = del.getPrice();

                table.addCell(date.toString());
                table.addCell(pId.toString());
                table.addCell(pName);
                table.addCell(price.toString());
                table.addCell(quantity.toString());
                table.addCell(tPrice.toString());
            }
            document.add(table);
            document.close();
        }catch (Exception e){

        }
    }
}
