package devel.exesoft.com.accshop.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import devel.exesoft.com.accshop.R;

public class PartnerTab3 extends Fragment {
    private static String TAG = "PartnerTab3";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true);
        Log.d(TAG, "onCreateView method");
        return inflater.inflate(R.layout.partner_tab_3, container, false);
    }
}
