package com.example.intentactions;

import androidx.annotation.IntegerRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.icu.number.IntegerWidth;
import android.media.Image;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private static final int CAMERA_REQUEST_CODE = 101;
    private static final int CAMERA_PERMISSION_REQUEST_CODE = 2021;
    private Button button;
    private ImageView imageView;
    private ConstraintLayout layout1;
    private int expanded = -1;
    private static int imgWidthFull;
    private static int imgWidthBack = 142;
    private static int imgHeightFull;
    private static int imgHeightBack = 203;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.button);
        imageView = (ImageView) findViewById(R.id.imageView);
        layout1 = (ConstraintLayout) findViewById(R.id.layout1);
        Log.d(TAG, "onCreate: width:" + imageView.getLayoutParams().width + " layout width:" + layout1.getLayoutParams().width);
        imgWidthBack = imageView.getLayoutParams().width;
        imgHeightBack = imageView.getLayoutParams().height;
        imgWidthFull = layout1.getLayoutParams().width; // width, height is -1 to denote full screen
        imgHeightFull = imgHeightBack * 2;

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.CAMERA}, CAMERA_PERMISSION_REQUEST_CODE);
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, CAMERA_REQUEST_CODE);

//                For sending Plain Text
//                intent.putExtra(Intent.EXTRA_TEXT, "Hello from the other side");
//                intent.setType("text/plain");
//                if (intent.resolveActivity(getPackageManager()) != null) {
//                    startActivity(intent);
//                } else {
//                    Toast.makeText(MainActivity.this, "Cannot Handle the Intent", Toast.LENGTH_SHORT).show();
//                }
            }
        });

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (expanded == -1) {
                    imageView.getLayoutParams().width = imgWidthFull;
                    imageView.getLayoutParams().height = imgHeightFull;
                    imageView.requestLayout();
//                    imageView.setMaxWidth((int)(density * imgWidthFull));
                    Log.d(TAG, "onClick: logFull");
                    expanded = 0;
                } else {
                    imageView.getLayoutParams().width = imgWidthBack;
                    imageView.getLayoutParams().height = imgHeightBack;
                    imageView.requestLayout();
                    Log.d(TAG, "onClick: logMin");
                    expanded = -1;
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case CAMERA_REQUEST_CODE:
                if (resultCode == RESULT_OK && data != null) {
                    Bundle bundle = data.getExtras();
                    if (bundle != null) {
                        Bitmap bitmap = (Bitmap) bundle.get("data");
                        Glide.with(this)
                                .load(bitmap)
                                .into(imageView);
                    }
                }
                break;
            default:
                break;
        }
    }
}