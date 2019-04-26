package devel.exesoft.com.accshop.adapters;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import devel.exesoft.com.accshop.R;
import devel.exesoft.com.accshop.view.StorageActivity;

public class StoreItemAdapter extends BaseAdapter {
    private List<Map<String, String>> mItems = new ArrayList();


    private StorageActivity mContext;

    private ViewHolder viewHolder;

    public StoreItemAdapter(StorageActivity pContext, ArrayList items)
    {
        mContext = pContext;
    }
    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public Map<String,String> getItem(int i) {
        return mItems.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null){
            view = mContext.getLayoutInflater().inflate(R.layout.store_list_item, viewGroup);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder)view.getTag();
        }

        viewHolder.storeItemName.setText(getItem(i).get("name"));
        viewHolder.storeItemBarcode.setText(getItem(i).get("barcode"));
        viewHolder.storeItemPrice.setText(getItem(i).get("price"));
        viewHolder.storeItemCount.setText(getItem(i).get("count"));
        viewHolder.storeItemUnit.setText(getItem(i).get("unit"));
        return view;
    }

    private  class ViewHolder{
        final TextView storeItemName;
        final TextView storeItemBarcode;
        final TextView storeItemCount;
        final TextView storeItemPrice;
        final TextView storeItemUnit;
        private ViewHolder(View view) {
            storeItemName = (TextView)view.findViewById(R.id.storeItemName);
            storeItemBarcode = (TextView)view.findViewById(R.id.storeItemBarcode);
            storeItemCount = (TextView)view.findViewById(R.id.storeItemCount);
            storeItemPrice = (TextView)view.findViewById(R.id.storeItemPrice);
            storeItemUnit = (TextView)view.findViewById(R.id.storeItemUnit);
        }
    }
}
