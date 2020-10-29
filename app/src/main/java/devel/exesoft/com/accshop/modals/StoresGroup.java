package devel.exesoft.com.accshop.modals;

import android.app.AlertDialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import devel.exesoft.com.accshop.R;
import devel.exesoft.com.accshop.adapters.StoreGroupAdapter;
import devel.exesoft.com.accshop.adapters.StoreItemAdapter;
import devel.exesoft.com.accshop.model.Item;
import devel.exesoft.com.accshop.model.StoreGroup;

public class StoresGroup {
    private Context context;
    private View dialogContent;
    private  AlertDialog.Builder storesDialog;
    private AlertDialog dialog;
    private View view;
    private ImageButton closeBtn;
    private ListView list;
    public StoresGroup(final Context context){
        this.context = context;
        storesDialog = new AlertDialog.Builder(context);
        view = getContent();
        storesDialog.setView(view);
        dialog = storesDialog.create();
        closeBtn = view.findViewById(R.id.storesGroupCloseBtn);
        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
        list = view.findViewById(R.id.storeGroupList);
    }
    private View getContent(){
        dialogContent = LayoutInflater.from(context).inflate(R.layout.stores_group,null);
        return dialogContent;
    }
    public void show(){
        dialog.show();
    }

    public void setItems(JSONObject jsonObject){
        if(jsonObject != null) {
            final ArrayList<StoreGroup> items = new ArrayList<>();
            try {
                if (jsonObject.getBoolean("success")) {
                    JSONArray data = jsonObject.getJSONArray("data");
                    if (data.length() > 0) {
                        items.clear();
                        for (int i = 0; i < data.length(); i++) {
                            final StoreGroup item = new StoreGroup();
                            item.setName(data.getJSONObject(i).getString("storage_name"));
                            item.setAmount(data.getJSONObject(i).getInt("amount"));
                            item.setUnit_string(data.getJSONObject(i).getString("unit_string"));
                            items.add(item);
                        }
                    }
                    list.setAdapter(new StoreItemAdapter(context, items));
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
