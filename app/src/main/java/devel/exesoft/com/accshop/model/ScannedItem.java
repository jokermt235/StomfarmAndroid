package devel.exesoft.com.accshop.model;

import java.util.Date;
import java.util.UUID;

public class ScannedItem {
    private String id;
    private String name;
    private String acc_code;
    private String barcode;
    private int count = 0;
    private String unit_string = "шт.";
    private Date created = new Date();
    private long store_id;
    private long price;
    private boolean debt  = false;

    private Item item;

    public ScannedItem(Item item){
        this.id = item.getId();
        this.name = item.getName();
        this.acc_code = item.getAcc_code();
        this.barcode = item.getBarcode();
        this.count = 1;
        this.unit_string = item.getUnit_string();
        this.price = item.getPrice();
        this.store_id = item.getStore_id();
        this.item = item;
    }

    public  Item  getItem(){
        return item;
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

    public void setDebt(boolean debt) {
        this.debt = debt;
    }

    public boolean getDebt(){
        return this.debt;
    }
}
