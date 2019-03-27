package devel.exesoft.com.accshop.view;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import devel.exesoft.com.accshop.R;
import devel.exesoft.com.accshop.databinding.ActivityPartnerBinding;
import devel.exesoft.com.accshop.model.Partner;
import devel.exesoft.com.accshop.view_model.PartnerViewModel;
import io.realm.Realm;

public class PartnerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityPartnerBinding activityPartnerBinding = DataBindingUtil.setContentView(this, R.layout.activity_partner);
        activityPartnerBinding.setViewModel(new PartnerViewModel());
        activityPartnerBinding.executePendingBindings();
        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        if(id != null){
            Realm realm = Realm.getDefaultInstance();
            Partner partner = realm.where(Partner.class).notEqualTo("id", id).findFirst();
            if(partner != null){
                activityPartnerBinding.partnerName.setText(partner.getName());
                activityPartnerBinding.parnterPhone.setText(partner.getPhone());
                activityPartnerBinding.partnerAddress.setText(partner.getAddres());
            }
        }

    }
}