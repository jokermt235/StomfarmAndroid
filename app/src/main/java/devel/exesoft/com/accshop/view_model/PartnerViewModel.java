package devel.exesoft.com.accshop.view_model;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import devel.exesoft.com.accshop.R;
import devel.exesoft.com.accshop.adapters.PartnerDebtAdapter;
import devel.exesoft.com.accshop.adapters.PartnerItemAdapter;
import devel.exesoft.com.accshop.adapters.PartnerPagerAdapter;
import devel.exesoft.com.accshop.adapters.PartnerSaleAapter;
import devel.exesoft.com.accshop.model.Debt;
import devel.exesoft.com.accshop.model.Item;
import devel.exesoft.com.accshop.model.Sale;
import devel.exesoft.com.accshop.model.ScannedItem;
import devel.exesoft.com.accshop.view.PartnerActivity;
import devel.exesoft.com.accshop.view.SimpleScannerActivity;
import io.realm.Realm;
import io.realm.RealmResults;

import static android.app.Activity.RESULT_OK;

public class PartnerViewModel extends Observable {
    private static String TAG = "PartnerViewModel";
    private PartnerActivity mContext;
    private static int REQUEST_CODE_SCANER = 1;
    private  static int REQUST_CODE_MANNUAL = 2;
    private ArrayList<Item> items = new ArrayList();
    private ArrayList<ScannedItem> scannedItems = new ArrayList();
    PartnerItemAdapter partnerItemAdapter;
    PartnerSaleAapter partnerSaleAapter;
    public PartnerViewModel(PartnerActivity pContext){
        mContext = pContext;
        mContext.activityPartnerBinding.partnerViewPager.setAdapter(
                new PartnerPagerAdapter(mContext.getSupportFragmentManager(), mContext.activityPartnerBinding.tablayoutPartner.getTabCount())
        );

        mContext.activityPartnerBinding.partnerViewPager.setOnPageChangeListener(
                new TabLayout.TabLayoutOnPageChangeListener(mContext.activityPartnerBinding.tablayoutPartner)
        );

        //mContext.activityPartnerBinding.tablayoutPartner.setupWithViewPager(mContext.activityPartnerBinding.partnerViewPager);

        partnerItemAdapter = new PartnerItemAdapter(mContext, scannedItems);

        mContext.activityPartnerBinding.tablayoutPartner.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mContext.activityPartnerBinding.partnerViewPager.setCurrentItem(tab.getPosition());
                Log.d(TAG, tab.getText().toString());
                switch (tab.getPosition()){
                    case 1: fillSaleList();break;
                    case 2: fillDebtList();break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        initDefaults();
    }

    private void initDefaults(){
        Realm realm = Realm.getDefaultInstance();
        RealmResults<Debt> results = realm.where(Debt.class).equalTo("partner_id", mContext.partner_id).findAll();
        long sum = 0;
        for(Debt debt : results){
            sum += (debt.getItem_price() * debt.getAmount());
        }
        mContext.activityPartnerBinding.clientDebt.setText(String.valueOf(sum));
    }

    public void onScanClicked(){
        mContext.startActivityForResult(
                new Intent(mContext, SimpleScannerActivity.class), 1);
    }

    public void onOkClicked(){
        Realm realm = Realm.getDefaultInstance();
        for(ScannedItem item : scannedItems) {
            if(item.getDebt()) {
                final Debt debt = new Debt();
                debt.setItem_id(item.getId());
                debt.setPartner_id(mContext.partner_id);
                debt.setAmount(item.getCount());
                debt.setItem_name(item.getName());
                debt.setItem_price(item.getPrice());
                debt.setItem_unit(item.getUnit_string());
                debt.setDebt_avg(item.getCount() * item.getPrice());
                realm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        realm.copyToRealm(debt);
                    }
                });
            }else{
                final Sale sale = new Sale();
                sale.setItem_id(item.getId());
                sale.setPartner_id(mContext.partner_id);
                sale.setAmount(item.getCount());
                sale.setItem_name(item.getName());
                sale.setItem_unit(item.getUnit_string());
                sale.setItem_price(item.getPrice());
                realm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        realm.copyToRealm(sale);
                    }
                });
            }

            final Item storeItem = item.getItem();
            if(storeItem.getCount() - item.getCount() <= 0) {
                realm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        // This will create a new object in Realm or throw an exception if the
                        // object already exists (same primary key
                        storeItem.setDeleted(true);
                        realm.copyToRealmOrUpdate(storeItem);
                    }
                });
            }else {
                final int cnt = storeItem.getCount() - item.getCount();
                realm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        // This will create a new object in Realm or throw an exception if the
                        // object already exists (same primary key
                        storeItem.setCount(cnt);
                        storeItem.setChanged(true);
                        realm.copyToRealmOrUpdate(storeItem);
                    }
                });
            }
        }
        realm.close();
        mContext.activityPartnerBinding.tablayoutPartner.getTabAt(1).select();
    }

    public  void onActivityResult(int requestCode, int resultCode, Intent data){
        if(resultCode == RESULT_OK){
            if(requestCode == REQUEST_CODE_SCANER){
                String[] itemBarcodes = data.getStringArrayExtra("item_barcodes");
                if(itemBarcodes != null) {
                    for (String barcode : itemBarcodes) {
                        Realm realm = Realm.getDefaultInstance();
                        Item  item = realm.where(Item.class).equalTo("barcode", barcode).findFirst();
                        scannedItems.add(new ScannedItem(item));
                    }
                }

            }
            if(requestCode == REQUST_CODE_MANNUAL){
                Log.d(TAG, "The request code is equal to " + String.valueOf(REQUST_CODE_MANNUAL));
                String id = data.getStringExtra("id");
                Realm realm = Realm.getDefaultInstance();
                Item  item = realm.where(Item.class).equalTo("id", id).findFirst();
                scannedItems.add(new ScannedItem(item));
            }
            if(scannedItems.size() > 0){
                ListView listView = (ListView)mContext.findViewById(R.id.scaned_item_list);
                listView.setAdapter(partnerItemAdapter);
                partnerItemAdapter.notifyDataSetChanged();
                setScannedCount();
            }
        }
    }

    public void removeScannedListviewItem(int position){
        scannedItems.remove(position);
        partnerItemAdapter.notifyDataSetChanged();
        setScannedCount();
    }

    public void setScannedCount(){
        int summ = 0;
        for(int i=0;i<partnerItemAdapter.getCount();i++){
            summ += partnerItemAdapter.getItem(i).getPrice() * partnerItemAdapter.getItem(i).getCount();
        }
        TextView scannedSumm = mContext.findViewById(R.id.scanned_summ);
        scannedSumm.setText(String.valueOf(summ));
    }

    public void fillSaleList(){
        ArrayList<Sale> saleItems  = new ArrayList();
        //items.add();
        Realm realm = Realm.getDefaultInstance();
        final RealmResults<Sale> items = realm.where(Sale.class).equalTo("partner_id", mContext.partner_id).findAll();
        ListView listView = (ListView)mContext.findViewById(R.id.partner_sale_list);
        for(Sale item : items){
            saleItems.add(item);
        }
        PartnerSaleAapter storeItemAdapter = new PartnerSaleAapter(mContext, saleItems);
        listView.setAdapter(storeItemAdapter);
    }

    public void fillDebtList(){
        ArrayList<Debt> debtItems  = new ArrayList();
        //items.add();
        Realm realm = Realm.getDefaultInstance();
        final RealmResults<Debt> items = realm.where(Debt.class).equalTo("partner_id", mContext.partner_id).findAll();
        ListView listView = (ListView)mContext.findViewById(R.id.partner_debt_list);
        for(Debt item : items){
            if(!item.getClc_status()) {
                debtItems.add(item);
            }
        }
        PartnerDebtAdapter storeItemAdapter = new PartnerDebtAdapter(mContext, debtItems);
        listView.setAdapter(storeItemAdapter);
    }

    public void setClcStatus(String item_id){
        Realm realm = Realm.getDefaultInstance();
        final Debt item = realm.where(Debt.class).equalTo("id", item_id).findFirst();

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                item.setClc_status(true);
                realm.copyToRealmOrUpdate(item);
            }
        });
        fillDebtList();
        realm.close();
    }
}
