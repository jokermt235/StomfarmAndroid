package devel.exesoft.com.accshop.view_model;

import java.util.Observable;

import devel.exesoft.com.accshop.model.Order;
import devel.exesoft.com.accshop.model.User;
import io.realm.Realm;

public class NewOrderModelView extends Observable {
    private static String TAG = "NewOrderModelView";
    public NewOrderModelView(){
        final Realm realm = Realm.getDefaultInstance();
        User user = realm.where(User.class).findFirst();
        if(user != null){
            final Order order = new Order();
            order.setUser_id(user.getId());
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    // This will create a new object in Realm or throw an exception if the
                    // object already exists (same primary key)
                    realm.copyToRealm(order);
                }
            });
            realm.close();
        }
    }
}
