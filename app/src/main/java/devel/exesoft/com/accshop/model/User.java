package devel.exesoft.com.accshop.model;

import java.util.Date;
import java.util.UUID;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class User extends RealmObject {
    @PrimaryKey
    private long id;
    private String mobile_id = UUID.randomUUID().toString();
    private Date created = new Date();
    private String fio;
    private String username;
    private String token;

    public void setFio(String fio){
        this.fio = fio;
    }

    public  void setUsername(String username){
        this.username = username;
    }

    public  void setToken(String token){
        this.token  = token;
    }

    public void setId(long id){
        this.id = id;
    }

    public long getId(){
        return  id;
    }

    public  String getFio(){
        return  fio;
    }

    public String getToken(){
        return token;
    }

    public String getUsername(){
        return username;
    }

    public String getMobile_id(){
        return mobile_id;
    }
}
