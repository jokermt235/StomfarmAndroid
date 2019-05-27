package devel.exesoft.com.accshop.model;

import java.util.Date;
import java.util.UUID;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Remain extends RealmObject {
    @PrimaryKey
    private String id = UUID.randomUUID().toString();
    private String item_name;
    private String acc_code;
    private String barcode;
    private int item_amount = 0;
    private String item_unit= "шт.";
    private Date created = new Date();
}
