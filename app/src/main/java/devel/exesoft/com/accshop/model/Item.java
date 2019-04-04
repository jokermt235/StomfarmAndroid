package devel.exesoft.com.accshop.model;

import java.util.Date;
import java.util.UUID;

import io.realm.RealmObject;

public class Item extends RealmObject {
    private String id = UUID.randomUUID().toString();
    private String name;
    private String acc_code;
    private String barcode;
    private Date created = new Date();
    private long store_id;

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
