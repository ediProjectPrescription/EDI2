package com.example.edi_1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class myadapter_for_patient extends FirebaseRecyclerAdapter<model_patient_recview,myadapter_for_patient.myviewholder> {

    public myadapter_for_patient(@NonNull FirebaseRecyclerOptions<model_patient_recview> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder myviewholder, int i, @NonNull model_patient_recview model_patient_recview) {
        myviewholder.tabname.setText(model_patient_recview.getTabname());
        myviewholder.time1.setText(model_patient_recview.getTime1());
        myviewholder.time3.setText(model_patient_recview.getTime3());
        myviewholder.time2.setText(model_patient_recview.getTime2());

    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerow_for_patient,parent,false);
        return new myviewholder(view);
    }

    class myviewholder extends RecyclerView.ViewHolder{
        ImageView img;
        TextView tabname,time1,time2,time3;
        public myviewholder(@NonNull View itemView) {
            super(itemView);


            tabname = (TextView)itemView.findViewById(R.id.tabletname);
            time1 = (TextView)itemView.findViewById(R.id.time1);
            time2 = (TextView)itemView.findViewById(R.id.time2);
            time3 = (TextView)itemView.findViewById(R.id.time3);
            img = (ImageView)itemView.findViewById(R.id.img1);
        }
    }
}
