package devel.exesoft.com.accshop.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import devel.exesoft.com.accshop.R;

public class HomeLeftMenuAdapter extends BaseAdapter {
        private Context mContext;

        public HomeLeftMenuAdapter(@NonNull Context context) {
            this.mContext = context;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = convertView;
            if (view == null) {
                LayoutInflater inflater = LayoutInflater.from(mContext);
                view = inflater.inflate(R.layout.home_left_menu_item, parent, false);
            }
            return view;
        }

        @Override
        public int getCount() {
            return 1;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }
}
