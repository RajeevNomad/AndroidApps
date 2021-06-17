package com.example.activitylifecycle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private TextView textView;
    private String name = "name2placeholder";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        android.util.Log.d(TAG, "onCreate: started");

        Button button = (Button) findViewById(R.id.btn1);
        textView = (TextView) findViewById(R.id.textView);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("name", "username");
                intent.putExtra("bundle", bundle);
                startActivity(intent);
            }
        });

        Button button2 = (Button) findViewById(R.id.btn2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText(name);
            }
        });

        if (savedInstanceState != null) {
            String name2 = savedInstanceState.getString("name");
            if (name2 != null) {
                Log.d(TAG, "onCreate: name2= " + name2);
                textView.setText(name2);
            }
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull @org.jetbrains.annotations.NotNull Bundle outState) {
        Log.d(TAG, "onSaveInstanceState: started");
        outState.putString("name", name);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onPause() {
        Log.d(TAG, "onPause: started");
        super.onPause();
    }

    @Override
    protected void onStart() {
        Log.d(TAG, "onStart: started");
        super.onStart();
    }

    @Override
    protected void onResume() {
        Log.d(TAG, "onResume: started");
        super.onResume();
    }

    @Override
    protected void onStop() {
        Log.d(TAG, "onStop: started");
        super.onStop();
    }

    @Override
    protected void onRestart() {
        Log.d(TAG, "onRestart: started");
        super.onRestart();
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "onDestroy: started");
        super.onDestroy();
    }
}