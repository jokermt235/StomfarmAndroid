package devel.exesoft.com.accshop.adapters;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import devel.exesoft.com.accshop.R;
import devel.exesoft.com.accshop.model.Item;
import devel.exesoft.com.accshop.view.PartnerActivity;

public class PartnerItemAdapter extends BaseAdapter {


    private ArrayList<Item> items = new ArrayList();

    private PartnerActivity mContext;

    private ViewHolder viewHolder;

    public PartnerItemAdapter(PartnerActivity pContext, ArrayList<Item> pItems)
    {
        mContext = pContext;
        items = pItems;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Item getItem(int i) {
        return items.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null){
            view = mContext.getLayoutInflater().inflate(R.layout.partner_scaned_item, viewGroup, false);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder)view.getTag();
        }
        viewHolder.scanedItemName.setText(getItem(i).getName());
        viewHolder.scanedItemBarcode.setText(getItem(i).getBarcode());
        viewHolder.getScanedItemCount.setText(String.valueOf(getItem(i).getCount()));
        return view;
    }

    private  class ViewHolder{
        final TextView scanedItemName;
        final TextView scanedItemBarcode;
        final TextView getScanedItemCount;
        private ViewHolder(View view) {
            this.scanedItemName = (TextView)view.findViewById(R.id.scaned_item_name);
            this.scanedItemBarcode = (TextView)view.findViewById(R.id.scaned_barcode);
            this.getScanedItemCount = (TextView)view.findViewById(R.id.scaned_item_count);
        }
    }
}
