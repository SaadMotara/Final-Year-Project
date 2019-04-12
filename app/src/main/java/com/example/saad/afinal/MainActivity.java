package com.example.saad.afinal;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    public static final int requestcode = 1;
    private static  final  String TAG = "Search permission";
    public static TextView resultText;
    Button scan_button;
    Button Map;
    Button Landmark;
    public static TextView CounterText;
    public int counter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        verifyPermissions();
        resultText = (TextView)findViewById(R.id.rResult);
        scan_button = (Button) findViewById(R.id.sScan);
        Map = (Button) findViewById(R.id.sMap);
        Landmark = (Button) findViewById(R.id.lLandmark);
        CounterText = (TextView)findViewById(R.id.Counter) ;
        counter = 0;




        scan_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ScanCode.class));
                counter ++;
                CounterText.setText(Integer.toString(counter));



            }
        });

        Map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MapsActivity.class));
            }
        });

        Landmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Database.class));
            }
        });



    }




    private void verifyPermissions() {
        Log.d(TAG, "veryfiy permission");
        String [] permission = {Manifest.permission.CAMERA, Manifest.permission.ACCESS_FINE_LOCATION};

        if(ContextCompat.checkSelfPermission(this.getApplicationContext(), permission[0]) == PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(this.getApplicationContext(), permission[1]) == PackageManager.PERMISSION_GRANTED) {

        } else {
            ActivityCompat.requestPermissions(MainActivity.this, permission, requestcode);
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
