package devel.exesoft.com.accshop.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import devel.exesoft.com.accshop.R;
import devel.exesoft.com.accshop.model.Sale;
import devel.exesoft.com.accshop.view.PartnerActivity;

public class PartnerSaleAapter extends BaseAdapter {

    private static String TAG = "PartnerSaleAdapater";
    ArrayList<Sale> mItems  = new ArrayList();
    private PartnerActivity mContext;
    private ViewHolder viewHolder;

    public PartnerSaleAapter(PartnerActivity mContext, ArrayList<Sale> mItems){
        this.mContext = mContext;
        this.mItems  = mItems;
    }
    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public Sale getItem(int position) {
        return mItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        if(view == null){
            view = LayoutInflater.from(mContext.getApplicationContext()).inflate(R.layout.partner_sale_item, parent, false);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder)view.getTag();
        }
        viewHolder.saleItemName.setText(getItem(position).getItem_name());
        viewHolder.saleItemDate.setText(getItem(position).getCreated().toString());
        viewHolder.saleItemAmount.setText(String.valueOf(getItem(position).getAmount()));
        viewHolder.saleItemUnit.setText(getItem(position).getItem_unit());
        viewHolder.saleItemAvgPrice.setText(String.valueOf(getItem(position).getAmount() * getItem(position).getItem_price()));
        return view;
    }

    private class ViewHolder{
        final TextView saleItemName;
        final TextView saleItemDate;
        final TextView saleItemAmount;
        final TextView saleItemUnit;
        final TextView saleItemAvgPrice;

        public ViewHolder(View view){
            this.saleItemName = view.findViewById(R.id.partner_sale_name);
            this.saleItemDate = view.findViewById(R.id.partner_sale_date);
            this.saleItemUnit = view.findViewById(R.id.partner_sale_unit);
            this.saleItemAmount = view.findViewById(R.id.partner_sale_amount);
            this.saleItemAvgPrice = view.findViewById(R.id.partner_sale_price);
        }
    }
}
