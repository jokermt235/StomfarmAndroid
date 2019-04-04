package devel.exesoft.com.accshop.adapters;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import devel.exesoft.com.accshop.R;
import devel.exesoft.com.accshop.view.PartnerActivity;

public class PartnerItemAdapter extends BaseAdapter {

    //private ArrayList<String,String> items = new ArrayList<String, String>();

    private Map<String, String> items = new HashMap<String, String>();

    private PartnerActivity mContext;

    private ViewHolder viewHolder;

    public PartnerItemAdapter(PartnerActivity pContext)
    {
        mContext = pContext;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int i) {
        return items.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null){
            view = mContext.getLayoutInflater().inflate(R.layout.partner_scaned_item, viewGroup);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder)view.getTag();
        }
        viewHolder.scanedItemName.setText("");
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
