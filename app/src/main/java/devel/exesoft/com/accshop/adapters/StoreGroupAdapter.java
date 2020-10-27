package devel.exesoft.com.accshop.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import devel.exesoft.com.accshop.R;
import devel.exesoft.com.accshop.model.Item;
import devel.exesoft.com.accshop.model.StoreGroup;

public class StoreGroupAdapter extends BaseAdapter {
    private List<StoreGroup> storeGroups = new ArrayList();

    private static  String TAG = "StoreGroupAdapter";
    private Context mContext;
    private StoreGroupAdapter.ViewHolder viewHolder;
    public StoreGroupAdapter(Context pContext, ArrayList items)
    {
        mContext = pContext;
        storeGroups = items;
    }
    @Override
    public StoreGroup getItem(int i) {
        return storeGroups.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null){
            view = LayoutInflater.from(mContext).inflate(R.layout.store_group_item, viewGroup , false);
            viewHolder = new StoreGroupAdapter.ViewHolder(view);
            view.setTag(viewHolder);
        }else{
            viewHolder = (StoreGroupAdapter.ViewHolder)view.getTag();
        }

        viewHolder.storeName.setText(getItem(i).getName());
        viewHolder.storeItemAmount.setText(String.valueOf(getItem(i).getAmount()));
        viewHolder.storeItemUnit.setText(getItem(i).getUnit_string());
        return view;
    }

    @Override
    public int getCount() {
        return storeGroups.size();
    }

    private  class ViewHolder{
        final TextView storeName;
        final TextView storeItemAmount;
        final TextView storeItemUnit;
        private ViewHolder(View view) {
           storeName = view.findViewById(R.id.storeGroupName);
           storeItemAmount = view.findViewById(R.id.storeGroupItemAmount);
           storeItemUnit  = view.findViewById(R.id.storeGroupItemUnit);
        }
    }
}
