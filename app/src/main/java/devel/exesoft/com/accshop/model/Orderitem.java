package devel.exesoft.com.accshop.model;

import java.util.Date;
import java.util.UUID;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Orderitem extends RealmObject {
    @PrimaryKey
    private String id = UUID.randomUUID().toString();
    private String name;
    private Date created = new Date();
    private long amount;
    private String partner_id;
    private String unit;
    private String barcode;
    private long price;

    public void setName(String pName){
        name = pName;
    }

    public String getName(){return name;}

    public void setAmount(long amount){this.amount = amount;}

    public  long getAmount(){return amount;}

    public void setPartner_id(String partner_id){this.partner_id = partner_id;}

    public String getPartner_id(){return partner_id;}

    public void setUnit(String unit){ this.unit = unit;}

    public String getUnit(){return unit;}

    public void setBarcode(String barcode){this.barcode = barcode;}

    public String getBarcode(){return barcode;}

    public  void setPrice(Long price){this.price = price;}

    public long getPrice(){return  this.price;}

    public String getId(){
        return id;
    }

}
