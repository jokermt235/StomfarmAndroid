package devel.exesoft.com.accshop.model;

import java.util.Date;
import java.util.UUID;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Debt extends RealmObject {
    @PrimaryKey
    private String id = UUID.randomUUID().toString();
    private String item_id;
    private String item_name;
    private long item_price;
    private String item_unit;
    private String partner_id;
    private long amount;
    private Date created = new Date();
    private Date clc_timestamp = new Date();
    private boolean clc_status = false;
    private  String status = "new";

    public String getId(){
        return  id;
    }

    public void setId(String id){
        this.id = id;
    }

    public void setStatus(String status){
        this.status = status;
    }

    public String getStatus(){
        return status;
    }

    public void setClc_status(boolean clc_status){
        this.clc_status = clc_status;
    }

    public  boolean getClc_status(){
        return  clc_status;
    }

    public String getItem_id(){
        return  item_id;
    }

    public void setItem_name(String item_name){
        this.item_name = item_name;
    }

    public void setItem_unit(String item_unit){
        this.item_unit = item_unit;
    }

    public String getItem_unit(){
        return  item_unit;
    }

    public Date getCreated(){
        return created;
    }

    public String getItem_name(){
        return  item_name;
    }

    public void  setItem_price(long item_price){
        this.item_price = item_price;
    }

    public long getItem_price(){
        return item_price;
    }

    public String getPartner_id() {
        return partner_id;
    }

    public  long getAmount(){
        return  amount;
    }

    public Date getClc_timestamp() {
        return clc_timestamp;
    }

    public void setItem_id(String item_id) {
        this.item_id = item_id;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public void setPartner_id(String partner_id) {
        this.partner_id = partner_id;
    }

    public void setClc_timestamp(Date clc_timestamp) {
        this.clc_timestamp = clc_timestamp;
    }
}
