package com.sldevs.panaghiusa_courierapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class PU_Update_Plastic extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;
    FirebaseListAdapter adaptor;

    ListView lvTBC;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pu_update);

        lvTBC = findViewById(R.id.lvTBC);

        String id = FirebaseAuth.getInstance().getUid();
        Query query = FirebaseDatabase.getInstance().getReference().child("TBP_PlasticAll");
        FirebaseListOptions<TBC_Items> o = new FirebaseListOptions.Builder<TBC_Items>()
                .setLayout(R.layout.c_items)
                .setQuery(query,TBC_Items.class)
                .build();
        adaptor = new FirebaseListAdapter(o) {
            @Override
            protected void populateView(@NonNull View v, @NonNull Object model, int position) {
                TextView tvID = v.findViewById(R.id.tvID);
                TextView tvName = v.findViewById(R.id.tvName);
                TextView tvNumber = v.findViewById(R.id.tvNumber);
                TextView tvDate = v.findViewById(R.id.tvDateTime);
                String date_time;


                TBC_Items tbc_items = (TBC_Items) model;
                tvID.setText("Contribution ID: " + tbc_items.getContributionid());
                tvName.setText("Fullname: " + tbc_items.getFullname());
                tvNumber.setText("Mobile No.: " + tbc_items.getNumber());
                date_time = tbc_items.getDate() + ", " + tbc_items.getTime();
                tvDate.setText("Date & Time: " + date_time);

                lvTBC.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        TextView tvID = view.findViewById(R.id.tvID);
                        TextView tvName = view.findViewById(R.id.tvName);
                        TextView tvNumber = view.findViewById(R.id.tvNumber);
                        TextView tvDate = view.findViewById(R.id.tvDateTime);

                        String id = tvID.getText().toString();
                        String fullname = tvName.getText().toString();
                        String number = tvNumber.getText().toString();
                        String date = tvDate.getText().toString();

                        Intent intent = new Intent(PU_Update_Plastic.this, PickUp_Update_Plastic.class);
                        intent.putExtra("type","Plastic");
                        intent.putExtra("id",id);
                        intent.putExtra("fullname",fullname);
                        intent.putExtra("number",number);
                        intent.putExtra("date",date);
                        startActivity(intent);

                        Toast.makeText(PU_Update_Plastic.this,fullname,Toast.LENGTH_SHORT).show();
                    }
                });
            }
        };

        lvTBC.setAdapter(adaptor);

    }
    @Override
    public void onStart() {
        super.onStart();
        adaptor.startListening();

    }

    @Override
    public void onStop() {
        super.onStop();
        adaptor.stopListening();
    }
}