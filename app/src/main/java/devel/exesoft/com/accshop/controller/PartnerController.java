package devel.exesoft.com.accshop.controller;

import android.content.Intent;
import android.util.Log;

import devel.exesoft.com.accshop.model.Partner;
import devel.exesoft.com.accshop.model.User;
import devel.exesoft.com.accshop.view.ClientsActivity;
import devel.exesoft.com.accshop.view.HomeActivity;
import devel.exesoft.com.accshop.view.PartnerActivity;
import io.realm.Realm;

public class PartnerController extends  AppController {
    private static  final String TAG  = "PartnerController";

    public static void add(final Partner partner){
        Log.d(TAG, "add method");
        Realm realm = Realm.getDefaultInstance();
        User user = realm.where(User.class).findFirst();
        if(user != null){
            partner.setUser_id(user.getId());

            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    // This will create a new object in Realm or throw an exception if the
                    // object already exists (same primary key)
                    realm.copyToRealm(partner);
                }
            });
            Intent intent = new Intent(getInstance(), ClientsActivity.class);
            getInstance().startActivity(intent);
        }
    }
}
