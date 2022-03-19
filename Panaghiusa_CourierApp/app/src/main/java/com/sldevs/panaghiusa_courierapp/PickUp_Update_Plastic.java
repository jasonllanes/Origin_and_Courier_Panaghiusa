package com.sldevs.panaghiusa_courierapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class PickUp_Update_Plastic extends AppCompatActivity {

    TextView tvType,tvID,tvName,tvAddress,tvNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_up_update);


        tvID = findViewById(R.id.tvID);
        tvName = findViewById(R.id.tvName);
        tvAddress = findViewById(R.id.tvAddress);
        tvNumber = findViewById(R.id.tvNumber);

        String id = getIntent().getStringExtra("id");
        String fullname = getIntent().getStringExtra("fullname");
        String addrees = getIntent().getStringExtra("address");
        String number = getIntent().getStringExtra("number");

        tvID.setText("User ID: " + id);
        tvName.setText("Fullname: " + fullname);
        tvAddress.setText("Address: " + addrees);
        tvNumber.setText("Mobile No.: " + number);




    }
}