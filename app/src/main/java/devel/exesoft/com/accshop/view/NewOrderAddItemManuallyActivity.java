package devel.exesoft.com.accshop.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONException;
import org.json.JSONObject;

import devel.exesoft.com.accshop.R;

public class NewOrderAddItemManuallyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_order_add_item_manually);
        Button mAddItemButton = (Button)findViewById(R.id.button_item_add_manually);

        mAddItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addItemManuallyToOrder();
            }
        });
    }

    private void addItemManuallyToOrder(){

        Intent mIntent = new Intent(NewOrderAddItemManuallyActivity.this, NewOrderActivtiy.class);

        JSONObject mParams = new JSONObject();

        EditText mName = findViewById(R.id.new_order_add_manually_edit_text_name);

        EditText mBarcode = findViewById(R.id.new_order_add_manually_barcode_text_edit);

        EditText mCount = findViewById(R.id.new_order_add_manually_count_edit_text);

        EditText  mPrice = findViewById(R.id.new_order_add_manually_price_edit_text);

        try {
            mParams.put("name", mName.getText());
            mParams.put("barcode", mBarcode.getText());
            mParams.put("price", mPrice.getText());
            mParams.put("count", mCount.getText());
        } catch (JSONException e) {
            e.printStackTrace();
        }


        mIntent.putExtra("new_item_data", mParams.toString());

        //ClientsActivity.this.startActivity();

        //NewOrderAddItemManuallyActivity.this.startActivity(mIntent);
        setResult(RESULT_OK, mIntent);
        NewOrderAddItemManuallyActivity.this.finish();
    }
}
