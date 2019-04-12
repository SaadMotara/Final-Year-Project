package com.example.saad.afinal;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Database extends AppCompatActivity {

    ListView listViewLandmark;

    DatabaseReference databaseReference;
    List<LandMark> landMarkList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);

        landMarkList = new ArrayList<>();

        databaseReference = FirebaseDatabase.getInstance().getReference("landmark");

        listViewLandmark = (ListView)findViewById(R.id.listViewLandMark);

    }

    @Override
    protected void onStart() {
        super.onStart();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                landMarkList.clear();

                for (DataSnapshot landmarkSnapshot: dataSnapshot.getChildren()){
                    LandMark landMark = landmarkSnapshot.getValue(LandMark.class);

                    landMarkList.add(landMark);
                }

                LandmarkList adapter = new LandmarkList(Database.this, landMarkList);
                listViewLandmark.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
