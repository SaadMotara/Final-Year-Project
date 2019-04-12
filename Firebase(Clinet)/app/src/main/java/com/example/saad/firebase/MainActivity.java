package com.example.saad.firebase;

import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText LandMarkname;
    Button AddLandmark;

    DatabaseReference databaseReference;

    ListView listView;
    List<landmark> landMarkList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        landMarkList = new ArrayList<landmark>();
        databaseReference = FirebaseDatabase.getInstance().getReference("landmark");
        LandMarkname = (EditText) findViewById(R.id.LandMark);
        AddLandmark = (Button)findViewById(R.id. AddLandmarks);
        listView = (ListView)findViewById(R.id.listView);

        AddLandmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addLandmarks();

            }
        });


        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                landmark landmark = landMarkList.get(position);

                showUpdateDialog(landmark.getLandmarkid(), landmark.getLandmarkname());
                return false;
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                landMarkList.clear();

                for (DataSnapshot landmarkSnapshot: dataSnapshot.getChildren()) {

                    landmark landmark = landmarkSnapshot.getValue(landmark.class);

                    landMarkList.add(landmark);
                }

                landmarkList adapter = new landmarkList(MainActivity.this, landMarkList);
                listView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void showUpdateDialog(final String landmarkid, final String landmarkname){

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();

        final View dialogView = inflater.inflate(R.layout.update_dialog, null);

        dialogBuilder.setView(dialogView);

        final EditText editTextName = (EditText) dialogView.findViewById(R.id.editName);
        final Button buttonUpdate = (Button)dialogView.findViewById(R.id.Update);

        dialogBuilder.setTitle("Updating Landmark"+landmarkname);

        final AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();

        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editTextName.getText().toString().trim();

                if(!TextUtils.isEmpty(name)) {
                    editTextName.setError("Location name requierd");
                    return;
                }

                updateLandmarks(landmarkid, landmarkname);

                alertDialog.dismiss();

            }
        });




    }

    private boolean updateLandmarks(String id, String name){

        databaseReference = FirebaseDatabase.getInstance().getReference("landmark").child(id);

        landmark landmark = new landmark(id, name);
        databaseReference.setValue(landmark);

        Toast.makeText(this, "Landmark location updated", Toast.LENGTH_SHORT).show();
        return true;

    }

    private void addLandmarks() {

        String LocationName = LandMarkname.getText().toString().trim();

        if(!TextUtils.isEmpty(LocationName)) {


           String id =  databaseReference.push().getKey();

           landmark landmark = new landmark(id, LocationName);

           databaseReference.child(id).setValue(landmark);

            Toast.makeText(this, "Landmark added", Toast.LENGTH_SHORT).show();

        }
        else {
            Toast.makeText(this, "Add Landmark name", Toast.LENGTH_SHORT).show();
        }


    }




    }

