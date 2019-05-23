package devel.exesoft.com.accshop.view;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import devel.exesoft.com.accshop.R;
import devel.exesoft.com.accshop.databinding.ActivityPartnerBinding;
import devel.exesoft.com.accshop.model.Debt;
import devel.exesoft.com.accshop.model.Partner;
import devel.exesoft.com.accshop.view_model.PartnerViewModel;
import io.realm.Realm;
import io.realm.RealmResults;

public class PartnerActivity extends AppCompatActivity {

    private static  String TAG = "PartnerActivity";

    public PartnerViewModel viewModel;

    public  String partner_id;

    public ActivityPartnerBinding activityPartnerBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        activityPartnerBinding = DataBindingUtil.setContentView(this, R.layout.activity_partner);
        viewModel = new PartnerViewModel(this);
        activityPartnerBinding.setViewModel(viewModel);
        activityPartnerBinding.executePendingBindings();
        partner_id = id;
        if(id != null){
            Realm realm = Realm.getDefaultInstance();
            Partner partner = realm.where(Partner.class).equalTo("id", id).findFirst();
            if(partner != null){
                activityPartnerBinding.parnterPhone.setText(partner.getPhone());
                activityPartnerBinding.partnerAddress.setText(partner.getAddress());
                activityPartnerBinding.toolbarPartner.setTitle(partner.getName());
            }
            realm.close();
        }

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){

        super.onActivityResult(requestCode, resultCode, data);

        viewModel.onActivityResult(requestCode, resultCode, data);

    }

    public void removeScannedListviewItem(int position){
        viewModel.removeScannedListviewItem(position);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
