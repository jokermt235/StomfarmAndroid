package devel.exesoft.com.accshop.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.ArrayList;

import devel.exesoft.com.accshop.R;
import devel.exesoft.com.accshop.model.Debt;
import devel.exesoft.com.accshop.model.DebtItem;
import devel.exesoft.com.accshop.view.PartnerActivity;

public class PartnerDebtAdapter extends BaseAdapter {

    private static String TAG = "PartnerDebtAdapter";
    ArrayList<Debt> mItems  = new ArrayList();
    private Context mContext;
    private ViewHolder viewHolder;

    public PartnerDebtAdapter(Context mContext, ArrayList<Debt> mItems){
        this.mContext = mContext;
        this.mItems  = mItems;
    }

    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public Debt getItem(int position) {
        return mItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        if(view == null){
            view = LayoutInflater.from(mContext.getApplicationContext()).inflate(R.layout.partner_debt_item, parent, false);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder)view.getTag();
        }
        final int index = position;
        viewHolder.debtItemName.setText(getItem(position).getItem_name());
        viewHolder.debtItemDate.setText(getItem(position).getCreated().toLocaleString());
        viewHolder.debtItemAmount.setText(String.valueOf(getItem(position).getAmount()));
        viewHolder.debtItemUnit.setText(getItem(position).getItem_unit());
        viewHolder.debtItemAvgPrice.setText(String.valueOf(getItem(position).getAmount() * getItem(position).getItem_price()));
        viewHolder.debtItemCancel.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    Log.d(TAG, getItem(index).getItem_name());
                }
            }
        });
        return view;
    }

    private class ViewHolder{
        final TextView debtItemName;
        final TextView debtItemDate;
        final TextView debtItemAmount;
        final TextView debtItemUnit;
        final TextView debtItemAvgPrice;
        final RadioButton debtItemCancel;

        public ViewHolder(View view){
            this.debtItemName = view.findViewById(R.id.partner_debt_name);
            this.debtItemDate = view.findViewById(R.id.partner_debt_date);
            this.debtItemUnit = view.findViewById(R.id.partner_debt_unit);
            this.debtItemAmount = view.findViewById(R.id.partner_debt_amount);
            this.debtItemAvgPrice = view.findViewById(R.id.partner_debt_price);
            this.debtItemCancel = view.findViewById(R.id.partner_debt_cancel);
        }
    }
}
