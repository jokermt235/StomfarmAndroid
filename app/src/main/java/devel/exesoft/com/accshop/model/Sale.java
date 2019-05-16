package devel.exesoft.com.accshop.model;

import java.util.Date;
import java.util.UUID;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Sale extends RealmObject {
    @PrimaryKey
    private String id = UUID.randomUUID().toString();
    private Date created = new Date();
    private String item_id;
    private String item_name;
    private String item_unit;
    private long amount;
    private long user_id;
    private long item_price;
    private String partner_id;

    public String getItem_id() {
        return item_id;
    }

    public String getItem_name(){
        return this.item_name;
    }

    public  void setItem_name(String item_name){
        this.item_name = item_name;
    }

    public void setItem_unit(String itme_unit){
        this.item_unit = itme_unit;
    }

    public String getItem_unit(){
        return  item_unit;
    }

    public void setItem_price(long item_price){
        this.item_price = item_price;
    }

    public long getItem_price(){
        return item_price;
    }

    public String getPartner_id() {
        return partner_id;
    }

    public String getId() {
        return id;
    }

    public long getAmount() {
        return amount;
    }

    public long getUser_id() {
        return user_id;
    }

    public Date getCreated() {
        return created;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public void setItem_id(String item_id) {
        this.item_id = item_id;
    }

    public void setPartner_id(String partner_id) {
        this.partner_id = partner_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

}
