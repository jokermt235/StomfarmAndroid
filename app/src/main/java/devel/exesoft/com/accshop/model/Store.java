package devel.exesoft.com.accshop.model;

import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Store extends RealmObject {
    @PrimaryKey
    private long id;
    private Date created = new Date();
    private String name;
    private long user_id;

    public long getId() {
         return  id;
    }

    public Date getCreated() {
        return created;
    }

    public String getName(){
        return  name;
    }

    public long getUser_id(){
        return user_id;
    }

    public void setUser_id(long user_id){
        this.user_id = user_id;
    }

    public void setName(String pName){
        name = pName;
    }
}
