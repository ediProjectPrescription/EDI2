package com.example.edi_1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class ViewForPatient extends AppCompatActivity {
    RecyclerView recview;
    myadapter_for_patient adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_for_patient);
        recview = (RecyclerView)findViewById(R.id.recview);
        recview.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<model_patient_recview> options =
                new FirebaseRecyclerOptions.Builder<model_patient_recview>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Prescription"), model_patient_recview.class)
                        .build();

        adapter = new myadapter_for_patient(options);
        recview.setAdapter(adapter);

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