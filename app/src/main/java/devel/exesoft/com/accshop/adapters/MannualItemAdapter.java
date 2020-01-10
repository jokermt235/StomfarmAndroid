package devel.exesoft.com.accshop.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import devel.exesoft.com.accshop.R;
import devel.exesoft.com.accshop.model.Item;
import devel.exesoft.com.accshop.view.MannualActivity;

public class MannualItemAdapter extends BaseAdapter {
    private List<Item> mItems = new ArrayList();


    private MannualActivity mContext;

    private ViewHolder viewHolder;

    public MannualItemAdapter(MannualActivity pContext, ArrayList items)
    {
        mContext = pContext;
        mItems = items;
    }
    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public Item getItem(int i) {
        return mItems.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null){
            view = LayoutInflater.from(mContext.getApplicationContext()).inflate(R.layout.store_list_item, viewGroup , false);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder)view.getTag();
        }
        viewHolder.storeItemId.setText(getItem(i).getId());
        viewHolder.storeItemName.setText(getItem(i).getName());
        viewHolder.storeItemBarcode.setText(getItem(i).getBarcode());
        viewHolder.storeItemPrice.setText(String.valueOf(getItem(i).getPrice()));
        viewHolder.storeItemCount.setText(String.valueOf(getItem(i).getCount()));
        viewHolder.storeItemUnit.setText(getItem(i).getUnit_string());
        return view;
    }

    private  class ViewHolder{
        final TextView storeItemId;
        final TextView storeItemName;
        final TextView storeItemBarcode;
        final TextView storeItemCount;
        final TextView storeItemPrice;
        final TextView storeItemUnit;
        private ViewHolder(View view) {
            storeItemId = view.findViewById(R.id.storeItemId);
            storeItemName = view.findViewById(R.id.storeItemName);
            storeItemBarcode = view.findViewById(R.id.storeItemBarcode);
            storeItemCount = view.findViewById(R.id.storeItemCount);
            storeItemPrice = view.findViewById(R.id.storeItemPrice);
            storeItemUnit = view.findViewById(R.id.storeItemUnit);
        }
    }
}
