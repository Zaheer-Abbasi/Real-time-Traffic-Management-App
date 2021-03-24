package com.example.traffic_jam;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    TextView detectedcars;
    View v1,v2,v3,v4,v5,v6;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference myref,myref2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        detectedcars = findViewById(R.id.textView22);
        firebaseDatabase = FirebaseDatabase.getInstance();

        v1= findViewById(R.id.view1);
        v2= findViewById(R.id.view2);
        v3= findViewById(R.id.view3);
        v4= findViewById(R.id.view4);
        v5= findViewById(R.id.view5);
        v6= findViewById(R.id.view6);


        invisibleviews();

        myref = firebaseDatabase.getReference("Data").child("-MUs0ySlsWWFtb-uJl-S");
        myref2 = firebaseDatabase.getReference("Data").child("cars_data");

        myref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.hasChildren()){
                    PointValue post = snapshot.getValue(PointValue.class);
                    if(post.getTraffic().equals("1")){
                        visibleviews();
                    }
                    else{
                        invisibleviews();
                    }
                    //for(DataSnapshot aa:snapshot.getChildren()) {

//                        PointValue add = aa.getValue(PointValue.class);
//                        detectedcars.setText("Detected\nCars"+post.getTraffic());
                   // }

                }
                else {
                    detectedcars.setText("Detected\nCars Null");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        myref2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.hasChildren()){
                    Cardata jj = snapshot.getValue(Cardata.class);
                    String aa = jj.getCars();
                    detectedcars.setText("Detected Cars  "+aa);

                    //for(DataSnapshot aa:snapshot.getChildren()) {

//                        PointValue add = aa.getValue(PointValue.class);
//                        detectedcars.setText("Detected\nCars"+post.getTraffic());
                    // }

                }
                else {
                    detectedcars.setText("Detected\nCars Null");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void invisibleviews() {
    v1.setVisibility(View.INVISIBLE);
    v2.setVisibility(View.INVISIBLE);
    v3.setVisibility(View.INVISIBLE);
    v4.setVisibility(View.INVISIBLE);
    v5.setVisibility(View.INVISIBLE);
    v6.setVisibility(View.INVISIBLE);
    }
    private void visibleviews() {
    v1.setVisibility(View.VISIBLE);
    v2.setVisibility(View.VISIBLE);
    v3.setVisibility(View.VISIBLE);
    v4.setVisibility(View.VISIBLE);
    v5.setVisibility(View.VISIBLE);
    v6.setVisibility(View.VISIBLE);
    }
}