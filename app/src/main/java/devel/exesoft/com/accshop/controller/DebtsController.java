package devel.exesoft.com.accshop.controller;

import devel.exesoft.com.accshop.model.Debt;
import devel.exesoft.com.accshop.model.Partner;
import io.realm.Realm;
import io.realm.RealmResults;

public class DebtsController extends AppController {
    private static  final String TAG  = "DebtsController";
    public static RealmResults<Debt> debts(){
        Realm realm = Realm.getDefaultInstance();
        RealmResults<Debt> debts = realm.where(Debt.class).findAll();
        return  debts;
    }
}
