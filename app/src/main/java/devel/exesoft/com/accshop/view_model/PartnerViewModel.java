package devel.exesoft.com.accshop.view_model;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.Observable;

import devel.exesoft.com.accshop.view.PartnerActivity;
import devel.exesoft.com.accshop.view.SimpleScannerActivity;

import static android.app.Activity.RESULT_OK;

public class PartnerViewModel extends Observable {
    private static String TAG = "PartnerViewModel";
    private PartnerActivity mContext;
    private static int REQUEST_CODE_SCANER = 1;
    private  static int REQUST_CODE_MANNUAL = 2;
    public PartnerViewModel(PartnerActivity pContext){
        mContext = pContext;
    }
    public void onScanClicked(){
        mContext.startActivityForResult(
                new Intent(mContext, SimpleScannerActivity.class), 1);
    }

    public  void onActivityResult(int requestCode, int resultCode, Intent data){
        if(resultCode == RESULT_OK){
            if(requestCode == REQUEST_CODE_SCANER){
                String[] itemBarcode = data.getStringArrayExtra("item_barcodes");
            }

            if(requestCode == REQUST_CODE_MANNUAL){
            }
        }
    }
}
