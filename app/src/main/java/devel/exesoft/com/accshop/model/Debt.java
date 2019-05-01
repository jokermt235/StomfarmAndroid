package devel.exesoft.com.accshop.model;

import java.util.Date;
import java.util.UUID;

import io.realm.RealmObject;

public class Debt extends RealmObject {
    private String id = UUID.randomUUID().toString();
    private long item_id;
    private long partner_id;
    private long amount;
    private int status;
    private Date clc_timestamp = new Date();

    public long getItem_id(){
        return  item_id;
    }

    public long getPartner_id() {
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

    public void setItem_id(long item_id) {
        this.item_id = item_id;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public void setPartner_id(long partner_id) {
        this.partner_id = partner_id;
    }

    public void setClc_timestamp(Date clc_timestamp) {
        this.clc_timestamp = clc_timestamp;
    }

    public void setStatus(int status){
        this.status = status;
    }
}
