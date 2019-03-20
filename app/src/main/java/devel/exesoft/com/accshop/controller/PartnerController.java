package devel.exesoft.com.accshop.controller;

import devel.exesoft.com.accshop.model.Partner;
import devel.exesoft.com.accshop.model.User;
import io.realm.Realm;

public class PartnerController extends  AppController {
    private static  final String TAG  = "PartnerController";

    public static void add(Partner partner){
        Realm realm = Realm.getDefaultInstance();
        User user = realm.where(User.class).findFirst();
        if(user != null){
            partner.setUser_id(user.getId());
        }
    }
}
