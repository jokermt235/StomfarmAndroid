package devel.exesoft.com.accshop.view;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import devel.exesoft.com.accshop.R;
import devel.exesoft.com.accshop.databinding.ActivityPartnerBinding;
import devel.exesoft.com.accshop.model.Partner;
import devel.exesoft.com.accshop.view_model.PartnerViewModel;
import io.realm.Realm;

public class PartnerActivity extends AppCompatActivity {

    private static  String TAG = "PartnerActivity";

    private PartnerViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityPartnerBinding activityPartnerBinding = DataBindingUtil.setContentView(this, R.layout.activity_partner);
        viewModel = new PartnerViewModel(this);
        activityPartnerBinding.setViewModel(viewModel);
        activityPartnerBinding.executePendingBindings();
        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        if(id != null){
            Realm realm = Realm.getDefaultInstance();
            Partner partner = realm.where(Partner.class).notEqualTo("id", id).findFirst();
            if(partner != null){
                activityPartnerBinding.parnterPhone.setText(partner.getPhone());
                activityPartnerBinding.partnerAddress.setText(partner.getAddres());
                activityPartnerBinding.toolbarPartner.setTitle(partner.getName());
            }
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){

        super.onActivityResult(requestCode, resultCode, data);

        viewModel.onActivityResult(requestCode, resultCode, data);

    }
}
