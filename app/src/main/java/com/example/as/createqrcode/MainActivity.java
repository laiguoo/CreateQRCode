package com.example.as.createqrcode;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentIntegrator intentIntegrator = new IntentIntegrator(MainActivity.this);
                intentIntegrator.initiateScan();
            }
        });

        ImageView iv = (ImageView) findViewById(R.id.iv);
//        Bitmap qrCodeBitmap = QRCodeUtil.createQRCodeBitmap("https://www.baidu.com", 480, 480);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.demo);
        Bitmap qrCodeBitmap = QRCodeUtil.createQRCodeBitmap("https://www.baidu.com", 480,
                "UTF-8", "H", "4", Color.BLACK, Color.WHITE,
                null, bitmap, 0.2F);
        iv.setImageBitmap(qrCodeBitmap);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult intentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (intentResult != null) {
            if (intentResult.getContents() == null) {
                Toast.makeText(this, "扫描取消", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "扫描结果：" + intentResult.getContents(),
                        Toast.LENGTH_SHORT).show();
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
