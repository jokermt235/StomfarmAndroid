package devel.exesoft.com.accshop.model;

import java.util.Date;
import java.util.UUID;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Item extends RealmObject {
    @PrimaryKey
    private String id = UUID.randomUUID().toString();
    private String name;
    private String acc_code;
    private String barcode;
    private int count = 0;
    private String unit_string = "шт.";
    private Date created = new Date();
    private long store_id;
    private long price;
    private boolean changed = false;


    public void setChanged(boolean changed) {
        this.changed = changed;
    }

    public boolean isChanged(){
        return changed;
    }

    public String getId(){
        return  id;
    }

    public String getAcc_code(){
        return acc_code;
    }

    public String getBarcode(){
        return barcode;
    }

    public Long getPrice(){
        return  price;
    }

    public void setCount(int pCount){
        count = pCount;
    }

    public int getCount(){
        return count;
    }

    public void setUnit_string(String unit_string) {
        this.unit_string = unit_string;
    }

    public String getUnit_string() {
        return unit_string;
    }

    public void setName(String pName){
        name = pName;
    }
    public String getName(){
        return  name;
    }

    public void setAcc_code(String pAccCode){
        acc_code = pAccCode;
    }

    public void setStore_id(long pStoreId){
        store_id = pStoreId;
    }

    public void setBarcode(String pBarcode){
        barcode = pBarcode;
    }

    public void setPrice(long price){
        this.price = price;
    }

    public long getStore_id(){
        return this.store_id;
    }
}
