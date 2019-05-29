package devel.exesoft.com.accshop.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import devel.exesoft.com.accshop.R;
import devel.exesoft.com.accshop.view.OrdersActivity;

public class OrderItemAdapter extends BaseAdapter {

    private ViewHolder viewHolder;

    private OrdersActivity mContext;

    public OrderItemAdapter(OrdersActivity mContext){
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        /*if(view == null){
            view = LayoutInflater.from(mContext.getApplicationContext()).inflate(R.layout.store_list_item, viewGroup , false);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder)view.getTag();
        }

        viewHolder.orderItemName.setText(getItem(i).getName());
        viewHolder.orderItemAvgPrice.setText(String.valueOf(getItem(i).getPrice()));
        viewHolder.orderItemAmount.setText(String.valueOf(getItem(i).getCount()));
        viewHolder.orderItemUnit.setText(getItem(i).getUnit_string());
        return view;*/
        return null;
    }

    private class ViewHolder{
        /*final TextView orderItemName;
        final TextView orderItemAmount;
        final TextView orderItemUnit;
        final TextView orderItemAvgPrice;

        public ViewHolder(View view){
            this.orderItemName = view.findViewById(R.id.partner_debt_name);
            this.orderDate = view.findViewById(R.id.partner_debt_date);
            this.orderItemUnit = view.findViewById(R.id.partner_debt_unit);
            this.orderItemAmount = view.findViewById(R.id.partner_debt_amount);
            this.debtItemAvgPrice = view.findViewById(R.id.partner_debt_price);
        }*/
    }
}
