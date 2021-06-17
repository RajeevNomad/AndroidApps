package com.example.challengeui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ImageView backArrow, settings, voice, cart, car;
    private EditText nameEdtTxt, emailEdtTxt, descriptionEdtTxt, websiteEdtTxt;
    private Spinner spinner;
    private RadioGroup radioGroup;
    private Button btnFinish;

    private String country = "";
    private ArrayList<String> countries;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initWidgets();

        countries = new ArrayList<>();
        countries.add("USA");
        countries.add("India");
        countries.add("Pakistan");
        countries.add("New Zealand");
        countries.add("France");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.support_simple_spinner_dropdown_item,
                countries);
        spinner.setAdapter(adapter);

        initOnClickListners();

        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finishRegistering();
            }
        });
    }

    private void initWidgets() {
        backArrow = (ImageView) findViewById(R.id.backArrow);
        settings = (ImageView) findViewById(R.id.settings);
        voice = (ImageView) findViewById(R.id.voice);
        cart = (ImageView) findViewById(R.id.cart);
        car = (ImageView) findViewById(R.id.car);

        nameEdtTxt = (EditText) findViewById(R.id.nameEdtTxt);
        emailEdtTxt = (EditText) findViewById(R.id.emailEdtTxt);
        descriptionEdtTxt = (EditText) findViewById(R.id.descriptionEdtTxt);
        websiteEdtTxt = (EditText) findViewById(R.id.websiteEdtTxt);

        spinner = (Spinner) findViewById(R.id.countrySpinner);

        radioGroup = (RadioGroup) findViewById(R.id.genderRadioGroup);

        btnFinish = (Button) findViewById(R.id.btnFinish);
    }

    private void finishRegistering() {
        String name = nameEdtTxt.getText().toString();
        String email = emailEdtTxt.getText().toString();
        String description = descriptionEdtTxt.getText().toString();
        String website = websiteEdtTxt.getText().toString();

        String gender = "";

        int checkedRb = radioGroup.getCheckedRadioButtonId();
        switch (checkedRb) {
            case R.id.rbMale:
                gender = "Male";
                break;
            case R.id.rbFemale:
                gender = "Female";
                break;
            case R.id.rbOther:
                gender = "Other";
                break;
            default:
                break;
        }

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                country = countries.get(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                country = "No country Selected";
            }
        });

        String finalMessage = "Name: " + name + "\nEmail: " + email + "\nDescription: " + description + "\nWebsite: " + website;

        Toast.makeText(this, finalMessage, Toast.LENGTH_SHORT).show();
        
    }

    private void initOnClickListners() {
        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Back Arrow Clicked", Toast.LENGTH_SHORT).show();
            }
        });
        
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Settings Tapped", Toast.LENGTH_SHORT).show();
            }
        });
        
        voice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Voice Selected", Toast.LENGTH_SHORT).show();
            }
        });
        
        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Cart Selected", Toast.LENGTH_SHORT).show();
            }
        });
        
        car.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Car Selected", Toast.LENGTH_SHORT).show();
            }
        });
    }
}