package devel.exesoft.com.accshop.model;

import java.util.Date;
import java.util.UUID;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Order extends RealmObject {
    @PrimaryKey
    private String id = UUID.randomUUID().toString();
    private Date created = new Date();
    private long user_id;
    private String item_id;
    private String partner_id;
    private long item_amount;
    private String item_unit;
    private long item_price;
    private boolean deleted = false;
    private boolean ready = true;

    public void setUser_id(Long user_id){
        this.user_id = user_id;
    }

    public long getUser_id(){
        return  user_id;
    }

    public boolean getDeleted(){
        return  deleted;
    }

    public void setDeleted(boolean deleted){
        this.deleted = deleted;
    }

    public void setReady(boolean ready){
        this.ready  = ready;
    }

    public void setItem_id(String item_id){
        this.item_id = item_id;
    }

    public String getItem_id(){
        return item_id;
    }

    public boolean getReady(){
        return this.ready;
    }

    public void setPartner_id(String partner_id){
        this.partner_id  = partner_id;
    }

    public String getPartner_id(){
        return  this.partner_id;
    }

    public void setItem_unit(String  item_unit){
        this.item_unit = item_unit;
    }

    public String getItem_unit() {
        return item_unit;
    }

    public void setItem_amount(long item_amount){
        this.item_amount = item_amount;
    }

    public long getItem_amount(){
        return item_amount;
    }

    public void setItem_price(long item_price){
        this.item_price = item_price;
    }

    public long getItem_price(){
        return this.item_price;
    }

    public  Date getCreated(){
        return created;
    }

    public String getId(){
        return id;
    }
}
