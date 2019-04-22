package fas.com.eddiejusto.fass;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import javax.xml.transform.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class scanner extends AppCompatActivity {

        private ZXingScannerView mScannerView;
        int po;
        String value;
        Context context;

        @Override
        public void onCreate(Bundle state) {
            super.onCreate(state);
            if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.M) {
                if (checkSelfPermission(Manifest.permission.CAMERA)
                        != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(new String[]{Manifest.permission.CAMERA},
                            100);
                }
            }
            mScannerView = new ZXingScannerView(this);   // Programmatically initialize the scanner view
            setContentView(mScannerView);                // Set the scanner view as the content view
        }

        @Override

        public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
            if (requestCode == 100) {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Permission Granted", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, "Permission Denied", Toast.LENGTH_LONG).show();
                }
            }}

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
            value = rawResult.getText();// Prints scan results
            uiui();
            //  Log.v(TAG, rawResult.getBarcodeFormat().toString()); // Prints the scan format (qrcode, pdf417 etc.)

            // If you would like to resume scanning, call this method below:
            //  mScannerView.resumeCameraPreview(this);
        }
        public void uiui(){
            try{
                po = Integer.parseInt(value);
            } catch (NumberFormatException nfe){
//            Toast.makeText(context, nfe+"", Toast.LENGTH_SHORT).show();
                System.out.print("could be parse" +nfe);
            }
            String op = String.valueOf(po);
            Intent myintent=new Intent(fas.com.eddiejusto.fass.scanner.this, QR.class).putExtra("PRODUCT_ID", op);
            startActivity(myintent);
        }
    }
