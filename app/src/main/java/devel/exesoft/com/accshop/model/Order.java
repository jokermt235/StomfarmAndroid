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
    private String status = "ready";

    public void setUser_id(Long user_id){
        this.user_id = user_id;
    }

    public long getUser_id(){
        return  user_id;
    }

    public String getStatus(){
        return  status;
    }
}
