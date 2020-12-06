package com.example.edi_1;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

public class myadapter_for_doctor extends FirebaseRecyclerAdapter<model_doctor_recview,myadapter_for_doctor.myviewholder> {

    public myadapter_for_doctor(@NonNull FirebaseRecyclerOptions<model_doctor_recview> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull final myviewholder myviewholder,final int position, @NonNull final model_doctor_recview model_doctor_recview) {
        myviewholder.tabname.setText(model_doctor_recview.getTabname());
        myviewholder.time1.setText(model_doctor_recview.getTime1());
        myviewholder.time3.setText(model_doctor_recview.getTime3());
        myviewholder.time2.setText(model_doctor_recview.getTime2());
                            myviewholder.edittab.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    final DialogPlus dialogPlus = DialogPlus.newDialog(myviewholder.tabname.getContext())
                                            .setContentHolder(new ViewHolder(R.layout.dialogcontent_for_doctor))
                                            .setExpanded(true,1100)
                                            .create();


                                    View myview = dialogPlus.getHolderView();
                                    EditText tabname = myview.findViewById(R.id.edittabnamedoc);
                                    EditText time1 = myview.findViewById(R.id.edittime1doc);
                                    EditText time2 = myview.findViewById(R.id.edittime2doc);
                                    EditText time3 = myview.findViewById(R.id.edittime3doc);
                                    Button submit = myview.findViewById(R.id.button6);

                                    tabname.setText(model_doctor_recview.getTabname());
                                    time1.setText(model_doctor_recview.getTime1());
                                    time2.setText(model_doctor_recview.getTime2());
                                    time3.setText(model_doctor_recview.getTime3());

                                    dialogPlus.show();
                                        submit.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                Map<String,Object> map = new HashMap<>();
                                                map.put("tabname",tabname.getText().toString());
                                                map.put("time1",time1.getText().toString());
                                                map.put("time2",time2.getText().toString());
                                                map.put("time3",time3.getText().toString());

                                                FirebaseDatabase.getInstance().getReference().child("Prescription")
                                                        .child(getRef(position).getKey()).updateChildren(map)
                                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                            @Override
                                                            public void onSuccess(Void aVoid) {
                                                                dialogPlus.dismiss();

                                                            }
                                                        })
                                                        .addOnFailureListener(new OnFailureListener() {
                                                            @Override
                                                            public void onFailure(@NonNull Exception e) {
                                                                dialogPlus.dismiss();

                                                            }
                                                        });
                                            }
                                        });


                                }
                            });

                            myviewholder.delettab.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(myviewholder.tabname.getContext());
                                    builder.setTitle("Delete Panel");
                                    builder.setMessage("Delete...");


                                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            FirebaseDatabase.getInstance().getReference().child("Prescription")
                                                    .child(getRef(position).getKey()).removeValue();



                                        }
                                    });
                                    builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                        }
                                    });
                                    builder.show();
                                }
                            });
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerow_for_doctor,parent,false);
        return new myviewholder(view);
    }

    class myviewholder extends RecyclerView.ViewHolder{
        ImageView img;
        ImageView edittab,delettab;
        TextView tabname,time1,time2,time3;
        public myviewholder(@NonNull View itemView) {
            super(itemView);


            tabname = (TextView)itemView.findViewById(R.id.tabletname);
            time1 = (TextView)itemView.findViewById(R.id.time1);
            time2 = (TextView)itemView.findViewById(R.id.time2);
            time3 = (TextView)itemView.findViewById(R.id.time3);
            img = (ImageView)itemView.findViewById(R.id.img1);
            edittab = (ImageView)itemView.findViewById(R.id.editTablet);
            delettab = (ImageView)itemView.findViewById(R.id.deleteTablet);
        }
    }
}
