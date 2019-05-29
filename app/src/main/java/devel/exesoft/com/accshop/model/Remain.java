package devel.exesoft.com.accshop.model;

import java.util.Date;
import java.util.UUID;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Remain extends RealmObject {
    @PrimaryKey
    private String id = UUID.randomUUID().toString();
    private String item_name;
    private String item_unit = "шт.";
    private long server_code;
    private String barcode;
    private long item_amount = 0;
    private Date created = new Date();

    public String getId(){
        return id;
    }

    public void setId(String id){
        this.id = id;
    }

    public long getServer_code(){
        return this.server_code;
    }

    public void setServer_code(long server_code){
        this.server_code = server_code;
    }

    public void setBarcode(String barcode){
        this.barcode = barcode;
    }

    public void setItem_amount(long item_amount){
        this.item_amount = item_amount;
    }

    public long getItem_amount(){
        return  this.item_amount;
    }

    public Date getCreated(){
        return this.created;
    }

    public void setCreated(Date created){
        this.created = created;
    }

    public String getBarcode(){
        return this.barcode;
    }

    public void setItem_name(String item_name){
        this.item_name = item_name;
    }

    public String getItem_name(){
        return this.item_name;
    }

    public void setItem_unit(String item_unit){
        this.item_unit = item_unit;
    }

    public String getItem_unit(){
        return item_unit;
    }
}
