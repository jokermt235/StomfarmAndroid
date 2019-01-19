package devel.exesoft.com.accshop;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class OrdersActivity extends AppCompatActivity {

    FloatingActionButton mFloatingButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);
        mFloatingButton = (FloatingActionButton)findViewById(R.id.add_new_order_button);

        mFloatingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OrdersActivity.this.startActivity(new Intent(OrdersActivity.this, NewOrderActivtiy.class));
            }
        });
    }
}
