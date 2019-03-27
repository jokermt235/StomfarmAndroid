package devel.exesoft.com.accshop.view_model;

import android.content.Intent;
import android.databinding.BaseObservable;
import android.util.Log;

import devel.exesoft.com.accshop.view.SimpleScannerActivity;
import devel.exesoft.com.accshop.view.StorageActivity;

import static android.app.Activity.RESULT_OK;

public class StoreViewModel extends BaseObservable {

    private StorageActivity mContext;

    private static int REQUEST_CODE_SCANER = 1;

    private static int REQUST_CODE_MANNUAL = 2;

    private static String TAG = "StoreViewModel";

    public StoreViewModel(StorageActivity pContext){
        mContext = pContext;
    }

    private StorageActivity getContext(){
        return mContext;
    }

    public void onScanClicked(){
        mContext.startActivityForResult(new Intent(mContext.getApplicationContext(), SimpleScannerActivity.class), REQUEST_CODE_SCANER);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if(resultCode == RESULT_OK){
            if(requestCode == REQUEST_CODE_SCANER){
                String itemBarcode = data.getStringExtra("item_barcode");
                Log.d(TAG, itemBarcode);
            }

            if(requestCode == REQUST_CODE_MANNUAL){
            }
        }
    }

}
