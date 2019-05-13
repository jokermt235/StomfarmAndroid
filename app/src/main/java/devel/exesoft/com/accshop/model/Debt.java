package devel.exesoft.com.accshop.model;

import java.util.Date;
import java.util.UUID;

import io.realm.RealmObject;

public class Debt extends RealmObject {
    private String id = UUID.randomUUID().toString();
    private String item_id;
    private String partner_id;
    private long amount;
    private int status = 0;
    private Date clc_timestamp = new Date();

    public String getItem_id(){
        return  item_id;
    }

    public String getPartner_id() {
        return partner_id;
    }

    public  long getAmount(){
        return  amount;
    }

    public  int getStatus(){
        return status;
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

    public void setStatus(int status){
        this.status = status;
    }
}
