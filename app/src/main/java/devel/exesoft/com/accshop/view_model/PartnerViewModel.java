package devel.exesoft.com.accshop.view_model;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Observable;

import devel.exesoft.com.accshop.R;
import devel.exesoft.com.accshop.adapters.PartnerItemAdapter;
import devel.exesoft.com.accshop.adapters.PartnerPagerAdapter;
import devel.exesoft.com.accshop.model.Item;
import devel.exesoft.com.accshop.view.PartnerActivity;
import devel.exesoft.com.accshop.view.SimpleScannerActivity;
import io.realm.Realm;

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

        mContext.activityPartnerBinding.tablayoutPartner.setupWithViewPager(mContext.activityPartnerBinding.partnerViewPager);
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
                        Realm realm = Realm.getDefaultInstance();
                        Item  item = realm.where(Item.class).equalTo("barcode", barcode).findFirst();
                        items.add(item);
                    }
                }

                if(items.size() > 0){
                    PartnerItemAdapter partnerItemAdapter = new PartnerItemAdapter(mContext, items);
                    partnerItemAdapter.notifyDataSetChanged();
                    ListView listView = (ListView)mContext.findViewById(R.id.scaned_item_list);
                    listView.setAdapter(partnerItemAdapter);
                    partnerItemAdapter.notifyDataSetChanged();
                }
            }
            if(requestCode == REQUST_CODE_MANNUAL){
            }
        }
    }

    public void removeScannedListviewItem(int position){
        items.remove(position);
        /*PartnerItemAdapter partnerItemAdapter = new PartnerItemAdapter(mContext, items);
        ListView listView = (ListView)mContext.findViewById(R.id.scaned_item_list);
        listView.setAdapter(partnerItemAdapter);*/
        ListView listView = (ListView)mContext.findViewById(R.id.scaned_item_list);
    }
}
