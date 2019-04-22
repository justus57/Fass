package fas.com.eddiejusto.fass;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Camera;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.zxing.qrcode.encoder.QRCode;

public class QR extends AppCompatActivity {

    EditText ed;
    Button bt;
    ImageView im;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr);
        ed = (EditText) findViewById(R.id.code);
        bt = (Button) findViewById(R.id.generate_qr);
        im =(ImageView) findViewById(R.id.imageView);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dd = ed.getText().toString();
                Bitmap myBitmap = QRCode.from(dd).bitmap();
                im.setImageBitmap(myBitmap);
            }
        });
    }
}
