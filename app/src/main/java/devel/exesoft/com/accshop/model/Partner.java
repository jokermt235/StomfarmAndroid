package devel.exesoft.com.accshop.model;

import java.util.Date;
import java.util.UUID;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Partner extends RealmObject {
    @PrimaryKey
    private String id = UUID.randomUUID().toString();
    private Date created = new Date();
    private String name;
    private String phone;
    private String address;
    private String status = "new";
    private long user_id;

    public void setId(String id){
        this.id = id;
    }

    public String getId(){
        return id;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setPhone(String phone){
        this.phone = phone;
    }

    public String getPhone(){
        return phone;
    }

    public void setAddress(String address){
        this.address = address;
    }

    public String getAddress(){
        return  address;
    }

    public void setUser_id(long user_id){
        this.user_id = user_id;
    }

    public long getUser_id(){
        return user_id;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus(){
        return  status;
    }
}
