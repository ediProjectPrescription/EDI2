package com.example.edi_1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.FirebaseDatabase;

public class ViewForDoctor extends AppCompatActivity {
    RecyclerView recview;
    myadapter_for_doctor adapter;
    Button AddTabletButton;
    FloatingActionButton fadd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_for_doctor);

        recview = (RecyclerView)findViewById(R.id.rcview);
        recview.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<model_doctor_recview> options =
                new FirebaseRecyclerOptions.Builder<model_doctor_recview>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Prescription"), model_doctor_recview.class)
                        .build();

        adapter = new myadapter_for_doctor(options);
        recview.setAdapter(adapter);

        AddTabletButton = (Button)findViewById(R.id.button5);

        AddTabletButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Add_Tablets.class);
                startActivity(intent);
            }
        });

        fadd = (FloatingActionButton)findViewById(R.id.floatingActionButton2);
        fadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(getApplicationContext(),Add_Tablets.class);
                startActivity(intent1);
            }
        });

    }
    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }
    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}