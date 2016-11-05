package shecklock.example.com.cleanration;


import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;

import java.util.ArrayList;
import java.util.List;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

/**
 * Created by sherlock on 4/11/16.
 */
public class fragment_qr extends Fragment implements ZXingScannerView.ResultHandler{

    private ZXingScannerView mScannerView;

    List<BarcodeFormat> formats;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_qr, container, false);
        LinearLayout linearLayout = (LinearLayout) rootView.findViewById(R.id.ll);

        formats = new ArrayList<>();
        formats.add(BarcodeFormat.QR_CODE);
        mScannerView = new ZXingScannerView(getContext());   // Programmatically initialize the scanner view
        mScannerView.setFormats(formats);



        return mScannerView;
    }

    @Override
    public void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this); // Register ourselves as a handler for scan results.
        mScannerView.startCamera();          // Start camera on resume
    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();           // Stop camera on pause
    }


    @Override
    public void handleResult(Result rawResult) {
        // Do something with the result here
        //Toast.makeText(getContext(), rawResult.getText(), Toast.LENGTH_SHORT).show();
        Log.v("TAG :", rawResult.getText()); // Prints scan results
        Log.v("TAG :", rawResult.getBarcodeFormat().toString()); // Prints the scan format (qrcode, pdf417 etc.)
        Intent intent = new Intent(getContext(), UserListActivity.class);
        intent.putExtra("Result", rawResult.getText());
        startActivity(intent);

        // If you would like to resume scanning, call this method below:
        mScannerView.resumeCameraPreview(this);
    }
}
