package devel.exesoft.com.accshop.view_model;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Observable;

import devel.exesoft.com.accshop.R;
import devel.exesoft.com.accshop.adapters.PartnerItemAdapter;
import devel.exesoft.com.accshop.adapters.PartnerPagerAdapter;
import devel.exesoft.com.accshop.controller.ItemController;
import devel.exesoft.com.accshop.model.Item;
import devel.exesoft.com.accshop.view.PartnerActivity;
import devel.exesoft.com.accshop.view.SimpleScannerActivity;

import static android.app.Activity.RESULT_OK;

public class PartnerViewModel extends Observable {
    private static String TAG = "PartnerViewModel";
    private PartnerActivity mContext;
    private static int REQUEST_CODE_SCANER = 1;
    private  static int REQUST_CODE_MANNUAL = 2;
    private ArrayList<Item> items = new ArrayList();
    public PartnerViewModel(PartnerActivity pContext){
        mContext = pContext;
        mContext.activityPartnerBinding.partnerViewPager.setAdapter(
                new PartnerPagerAdapter(mContext.getSupportFragmentManager(), mContext.activityPartnerBinding.tablayoutPartner.getTabCount())
        );

        mContext.activityPartnerBinding.partnerViewPager.setOnPageChangeListener(
                new TabLayout.TabLayoutOnPageChangeListener(mContext.activityPartnerBinding.tablayoutPartner)
        );
    }
    public void onScanClicked(){
        mContext.startActivityForResult(
                new Intent(mContext, SimpleScannerActivity.class), 1);
    }

    public  void onActivityResult(int requestCode, int resultCode, Intent data){
        if(resultCode == RESULT_OK){
            if(requestCode == REQUEST_CODE_SCANER){
                String[] itemBarcodes = data.getStringArrayExtra("item_barcodes");
                if(itemBarcodes != null) {
                    for (String barcode : itemBarcodes) {
                        Item item = ItemController.getItemByBarcode(barcode);
                        items.add(item);
                    }
                }

                if(items.size() > 0){
                    PartnerItemAdapter partnerItemAdapter = new PartnerItemAdapter(mContext, items);
                    ListView listView = (ListView)mContext.findViewById(R.id.scaned_item_list);
                    listView.setAdapter(partnerItemAdapter);
                }
            }
            if(requestCode == REQUST_CODE_MANNUAL){
            }
        }
    }
}
