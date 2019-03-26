package devel.exesoft.com.accshop.view_model;

import android.content.Context;
import android.databinding.BaseObservable;

import devel.exesoft.com.accshop.controller.StoreContoller;
import devel.exesoft.com.accshop.model.Store;
import io.realm.Realm;

public class StoreViewModel extends BaseObservable {

    private Context mContext;

    public StoreViewModel(Context pContext){
        mContext = pContext;
    }

    private Context getContext(){
        return mContext;
    }


}
