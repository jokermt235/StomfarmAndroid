package devel.exesoft.com.accshop.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.zxing.Result;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class SimpleScannerActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {

    String TAG = "SimpleScannerActivity";

    private ZXingScannerView mScannerView;

    private List<String> barcodes  = new ArrayList<>();

    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);
        mScannerView = new ZXingScannerView(this);   // Programmatically initialize the scanner view
        setContentView(mScannerView);                // Set the scanner view as the content view
    }

    @Override
    public void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this); // Register ourselves as a handler for scan results.
        mScannerView.startCamera();          // Start camera on resume

        Log.e(TAG, "CAMERA WAS INITIALIZED");
    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();      // Stop camera on pause
    }

    @Override
    public void handleResult(Result rawResult) {
        // Do something with the result here
        //Log.v(TAG, rawResult.getText()); // Prints scan results
        //Log.v(TAG, rawResult.getBarcodeFormat().toString()); // Prints the scan format (qrcode, pdf417 etc.)

        // If you would like to resume scanning, call this method below:
        mScannerView.resumeCameraPreview(this);

        Intent mIntent = new Intent(SimpleScannerActivity.this, PartnerActivity.class);
        barcodes.add(rawResult.toString());
        mIntent.putExtra("item_barcodes", barcodes.toArray(new String[barcodes.size()]));
        setResult(RESULT_OK, mIntent);
        //SimpleScannerActivity.this.finish();
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
        SimpleScannerActivity.this.finish();
    }
}
