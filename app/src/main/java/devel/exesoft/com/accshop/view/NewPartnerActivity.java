package devel.exesoft.com.accshop.view;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import devel.exesoft.com.accshop.R;
import devel.exesoft.com.accshop.databinding.ActivityNewPartnerBinding;
import devel.exesoft.com.accshop.view_model.NewPartnerViewModel;

public class NewPartnerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityNewPartnerBinding activityNewPartnerBinding = DataBindingUtil.setContentView(this, R.layout.activity_new_partner);
        NewPartnerViewModel newPartnerViewModel = new NewPartnerViewModel();
        activityNewPartnerBinding.setViewModel(newPartnerViewModel);
        activityNewPartnerBinding.executePendingBindings();
    }
}
