package com.example.trax;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class tracker_service extends Service {
    private LocationListener locationListener;
    private LocationManager locationManager;
    FirebaseDatabase firebaseDatabase;
    private BroadcastReceiver broadcastReceiver;
    private DatabaseReference databaseReference;
    private Context context;

    public Context getContext() {
        this.firebaseDatabase=firebaseDatabase;
        return context;
    }


/*    firebaseDatabase=FirebaseDatabase.getInstance();

    databaseReference = firebaseDatabase.getInstance().getReference("Location");*/

    double lat;
    double lang;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onCreate() {
        locationListener=new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {
                Intent i=new Intent("loca_update");

                lat=location.getLatitude();
                lang=location.getLongitude();
                i.putExtra("lat",location.getLatitude());
                i.putExtra("lang",location.getLongitude());
                sendBroadcast(i);
       /*         databaseReference.child("latitude").push().setValue(lat);
                databaseReference.child("longitude").push().setValue(lang);*/
             //   String s=addresses.get(0).getFeatureName() + ", " + addresses.get(0).getLocality() +", " + addresses.get(0).getAdminArea() + ", " + addresses.get(0).getCountryName();

                Log.d("location",lat+"-"+lang);
                Toast.makeText(getApplicationContext(),"lang: " +lang+ " lat: "+lat,Toast.LENGTH_SHORT).show();
            }
        };
        locationManager=(LocationManager) getApplicationContext().getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,7000,0,locationListener);

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        return Service.START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(locationManager==null){
            locationManager.removeUpdates(locationListener);
        }
    }
}
