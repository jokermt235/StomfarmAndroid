package devel.exesoft.com.accshop.model;

import java.util.Date;
import java.util.UUID;

import io.realm.RealmObject;

public class Item extends RealmObject {
    private String id = UUID.randomUUID().toString();
    private String name;
    private String acc_code;
    private String barcode;
    private int count = 0;
    private String unit_string = "шт.";
    private Date created = new Date();
    private long store_id;

    public String getId(){
        return  id;
    }

    public String getAcc_code(){
        return acc_code;
    }

    public String getBarcode(){
        return barcode;
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
}
