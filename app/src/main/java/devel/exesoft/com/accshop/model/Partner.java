package devel.exesoft.com.accshop.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Partner extends RealmObject {
    @PrimaryKey
    private long id;
    private String name;
    private String phone;
    private String address;
    private String status;
    private long user_id;

    public void setId(long id){
        this.id = id;
    }

    public long getId(){
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

    public void setAddres(String address){
        this.address = address;
    }

    public String getAddres(){
        return  address;
    }

    public void setUser_id(long user_id){
        this.user_id = user_id;
    }

    public long getUser_id(){
        return user_id;
    }
}
