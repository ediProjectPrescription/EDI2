package com.example.edi_1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Add_Tablets extends AppCompatActivity {

    EditText tabletName,t1,t2,t3,Tabname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__tablets);
    }

    public void process(View view) {

        tabletName = (EditText)findViewById(R.id.editTabletName);
        t1 = (EditText)findViewById(R.id.editTime1);
        t2 = (EditText)findViewById(R.id.editTime2);
        t3 = (EditText)findViewById(R.id.editTime3);
        Tabname = (EditText)findViewById(R.id.editTabletName);

        String Tabletname = tabletName.getText().toString().trim();
        String tabname = tabletName.getText().toString().trim();
        String time1 = t1.getText().toString().trim();
        String time2 = t2.getText().toString().trim();
        String time3 = t3.getText().toString().trim();


        dataholder_P_TabletData obj= new dataholder_P_TabletData(time1,time2,time3,tabname);

        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference node = db.getReference("Prescription");

        node.child(Tabletname).setValue(obj);
        t1.setText("");
        t2.setText("");
        t3.setText("");
        Tabname.setText(Tabletname);

        Toast.makeText(getApplicationContext(),"value inserted",Toast.LENGTH_LONG).show();
    }
}