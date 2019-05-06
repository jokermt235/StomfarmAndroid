package devel.exesoft.com.accshop.adapters;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

import devel.exesoft.com.accshop.R;
import devel.exesoft.com.accshop.model.Item;
import devel.exesoft.com.accshop.view.PartnerActivity;

public class PartnerItemAdapter extends BaseAdapter {

    private static  String TAG = "PartnerBaseAdapter";

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
    public View getView(final int i, View view, ViewGroup viewGroup) {
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
        viewHolder.scanedDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mContext.removeScannedListviewItem(i);
            }
        });


        return view;
    }

    private  class ViewHolder{
        final TextView scanedItemName;
        final TextView scanedItemBarcode;
        final TextView getScanedItemCount;
        final ImageButton scanedDeleteButton;
        final ImageButton scannedIncButton;
        final ImageButton scannedDecButton;
        private ViewHolder(View view) {
            this.scanedItemName = (TextView)view.findViewById(R.id.scaned_item_name);
            this.scanedItemBarcode = (TextView)view.findViewById(R.id.scaned_barcode);
            this.getScanedItemCount = (TextView)view.findViewById(R.id.scaned_item_count);
            this.scanedDeleteButton = view.findViewById(R.id.scanned_remove_item);
            this.scannedIncButton = view.findViewById(R.id.scaned_item_incr);
            this.scannedDecButton = view.findViewById(R.id.scaned_item_dicr);
            scannedIncButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String newCount = "";
                    String count = getScanedItemCount.getText().toString();
                    newCount = String.valueOf(1 + Integer.valueOf(count));
                    getScanedItemCount.setText(newCount);
                }
            });

            scannedDecButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String count = getScanedItemCount.getText().toString();
                    if(Integer.valueOf(count) > 1) {
                        String newCount = "";
                        newCount = String.valueOf(Integer.valueOf(count) - 1);
                        getScanedItemCount.setText(newCount);
                    }
                }
            });
        }
    }
}
