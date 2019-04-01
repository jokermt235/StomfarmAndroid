package devel.exesoft.com.accshop.model;

import java.util.Date;
import java.util.UUID;

import io.realm.RealmObject;

public class Orderitem extends RealmObject {
    private String id = UUID.randomUUID().toString();
    private String name;
    private String item_id;
    private Date created = new Date();
    private long store_id;

    public void setName(String pName){
        name = pName;
    }

    public String getId(){
        return item_id;
    }

    public void itemId(String pItemId){
        item_id = pItemId;
    }

    public void setStore_id(long pStoreId){
        store_id = pStoreId;
    }

}
