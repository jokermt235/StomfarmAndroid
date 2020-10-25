package devel.exesoft.com.accshop.modals;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;

import devel.exesoft.com.accshop.R;

public class StoresGroup {
    private Context context;
    private View dialogContent;
    private  AlertDialog.Builder storesDialog;
    private AlertDialog dialog;
    private View view;
    private ImageButton closeBtn;
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
    }
    private View getContent(){
        dialogContent = LayoutInflater.from(context).inflate(R.layout.stores_group,null);
        return dialogContent;
    }
    public void show(){
        dialog.show();
    }
}
