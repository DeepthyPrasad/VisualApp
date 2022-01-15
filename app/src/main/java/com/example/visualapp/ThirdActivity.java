package com.example.visualapp;


import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.logging.Logger;


public class ThirdActivity extends AppCompatActivity implements LocationListener {
    //for location
    TextView textViewcity;
    TextView textViewg;
    String city = "Unknown";
    double longitude ;
    double latitude ;
    boolean canGetLocation = false;
    private final Context context = this;
    boolean isGPSEnabled = false;
    boolean isNetworkEnabled = false;
    //int MY_PERMISSION_ACCESS_COARSE_LOCATION = 1;
    public static final int REQUEST_CODE_PERMISSIONS = 101;
    Location location;
    //minimum distance to change updates in metres;
    private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 10;
    // The minimum time between updates in milliseconds
    private static final long MIN_TIME_BW_UPDATES = 1000 * 60 * 1; // 1 minute
    // Declaring a Location Manager
    protected LocationManager locationManager;


    private String[] permissions;
    private DBHandler dbHandler;


    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.third_activity);

        checkPermission();
        textViewcity = (TextView) findViewById(R.id.textView3);
        textViewg = (TextView) findViewById(R.id.textView4);

        // creating a new db handler class and passing our context to it.
        try {
            dbHandler = new DBHandler(ThirdActivity.this);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Add options to fit the image in the app screen
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inDensity =  DisplayMetrics.DENSITY_DEFAULT;


        {

            locationManager = (LocationManager) context.getSystemService(LOCATION_SERVICE);

            //getting GPS status
            isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);

            //getting network status
            isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);


            if (!isGPSEnabled && !isNetworkEnabled) {
                Log.v("entering ", "if");
            } else {
                //this.canGetLocation = true;
                Log.v("network", "service");

                if(isGPSEnabled)
                {
                    if(location == null &&  this.canGetLocation == true )
                    {
                        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, MIN_TIME_BW_UPDATES, MIN_DISTANCE_CHANGE_FOR_UPDATES, this);

                        if(locationManager != null)
                        {

                            location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

                            if(location != null)
                            {
                                latitude = location.getLatitude();
                                longitude = location.getLongitude();

                            }

                        }

                    }
                }
                //getting location from network provider
                else if (isNetworkEnabled) {

                    locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, MIN_TIME_BW_UPDATES, MIN_DISTANCE_CHANGE_FOR_UPDATES, this);

                    if(locationManager != null)
                    {
                        location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

                        if(location != null)
                        {
                            latitude = location.getLatitude();
                            longitude = location.getLongitude();
                        }
                    }
                }

            }

            longitude = getLongitude();
            latitude = getLatitude();
            System.out.println("The Longi from main" +longitude);
            System.out.println("The Lati from main " +latitude);
            city = getLocality(this);
            System.out.println("The City from main " +city);
            textViewcity.setText(city);


            if ((latitude >= 57.00000) && (latitude < 58.00000) && (longitude >= 11.80000) && (longitude < 13.00000)) {
                //Gothenburg Region
                textViewg.setText("Visualizing Gothenburg region");
                ImageView imageViewScatterGot = (ImageView) findViewById(R.id.imageViewScatter);
                ArrayList<StoreData> imagesDbScaGot = dbHandler.readImages("scattergot");
                byte[] bytScaGot = imagesDbScaGot.get(0).getImage();
                Bitmap bmpScaGot = BitmapFactory.decodeByteArray(bytScaGot, 0, bytScaGot.length, options);
                imageViewScatterGot.setImageBitmap(bmpScaGot);

                ImageView imageViewBarGot=(ImageView) findViewById(R.id.imageViewBar);
                ArrayList<StoreData> imagesDbBarGot = dbHandler.readImages("bargot");
                byte[] bytBarGot = imagesDbBarGot.get(0).getImage();
                Bitmap bmpBarGot = BitmapFactory.decodeByteArray(bytBarGot, 0 ,bytBarGot.length, options);
                imageViewBarGot.setImageBitmap(bmpBarGot);

                ImageView imageViewPieGot=(ImageView) findViewById(R.id.imageViewPie);
                ArrayList<StoreData> imagesDbPieGot = dbHandler.readImages("piegot");
                byte[] bytPieGot = imagesDbPieGot.get(0).getImage();
                Bitmap bmpPieGot = BitmapFactory.decodeByteArray(bytPieGot, 0 ,bytPieGot.length, options);
                imageViewPieGot.setImageBitmap(bmpPieGot);

            }else if ((latitude >= 55.00000) && (latitude < 56.90000) && (longitude >= 11.90000) && (longitude < 13.50000)){
                //Malmo Region
                textViewg.setText("Visualizing Malmo region");
                ImageView imageViewScatterMal=(ImageView) findViewById(R.id.imageViewScatter);
                ArrayList<StoreData> imagesDbScaMal = dbHandler.readImages("scattermal");
                byte[] bytScaMal = imagesDbScaMal.get(0).getImage();
                Bitmap bmpScaMal = BitmapFactory.decodeByteArray(bytScaMal, 0 ,bytScaMal.length,options);
                imageViewScatterMal.setImageBitmap(bmpScaMal);

                ImageView imageViewBarMal=(ImageView) findViewById(R.id.imageViewBar);
                ArrayList<StoreData> imagesDbBarMal = dbHandler.readImages("barmal");
                byte[] bytBarMal = imagesDbBarMal.get(0).getImage();
                Bitmap bmpBarMal = BitmapFactory.decodeByteArray(bytBarMal, 0 ,bytBarMal.length, options);
                imageViewBarMal.setImageBitmap(bmpBarMal);

                ImageView imageViewPieMal=(ImageView) findViewById(R.id.imageViewPie);
                ArrayList<StoreData> imagesDbPieMal = dbHandler.readImages("piemal");
                byte[] bytPieMal = imagesDbPieMal.get(0).getImage();
                Bitmap bmpPieMal = BitmapFactory.decodeByteArray(bytPieMal, 0 ,bytPieMal.length, options);
                imageViewPieMal.setImageBitmap(bmpPieMal);
            }
            else{
                //Stockholm Region
                textViewg.setText("Visualizing Stockholm region");
                ImageView imageViewScatterStm=(ImageView) findViewById(R.id.imageViewScatter);
                ArrayList<StoreData> imagesDbScaStm = dbHandler.readImages("scatterstm");
                byte[] bytScaStm = imagesDbScaStm.get(0).getImage();
                Bitmap bmpScaStm = BitmapFactory.decodeByteArray(bytScaStm, 0 ,bytScaStm.length,options);
                imageViewScatterStm.setImageBitmap(bmpScaStm);

                ImageView imageViewBarStm=(ImageView) findViewById(R.id.imageViewBar);
                ArrayList<StoreData> imagesDbBarStm = dbHandler.readImages("barstm");
                byte[] bytBarStm = imagesDbBarStm.get(0).getImage();
                Bitmap bmpBarStm = BitmapFactory.decodeByteArray(bytBarStm, 0 ,bytBarStm.length, options);
                imageViewBarStm.setImageBitmap(bmpBarStm);

                ImageView imageViewPieStm=(ImageView) findViewById(R.id.imageViewPie);
                ArrayList<StoreData> imagesDbPieStm = dbHandler.readImages("piestm");
                byte[] bytPieStm = imagesDbPieStm.get(0).getImage();
                Bitmap bmpPieStm = BitmapFactory.decodeByteArray(bytPieStm, 0 ,bytPieStm.length, options);
                imageViewPieStm.setImageBitmap(bmpPieStm);
            }
        }
    }
    public void checkPermission(){
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // Check Permissions Now
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, REQUEST_CODE_PERMISSIONS);


        } else {
            Toast.makeText(getApplicationContext(), "Permission granted", Toast.LENGTH_SHORT).show();
            this.canGetLocation =true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String[] permissions,
                                           int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE_PERMISSIONS) {

            //boolean foreground = false;

            for (int i = 0; i < permissions.length; i++) {
                if (permissions[i].equalsIgnoreCase(Manifest.permission.ACCESS_BACKGROUND_LOCATION)) {
                    //foreground permission allowed
                    if (grantResults[i] >= 0) {
                        //foreground = true;
                        Toast.makeText(getApplicationContext(), "Foreground location permission allowed", Toast.LENGTH_SHORT).show();
                        continue;

                    } else {
                        Toast.makeText(getApplicationContext(), "Location Permission denied", Toast.LENGTH_SHORT).show();
                        break;
                    }
                }
            }

        }

    }
        //override methods
        @Override
        public void onLocationChanged(Location location) {
        }

        @Override
        public void onProviderDisabled(String provider) {
        }

        @Override
        public void onProviderEnabled(String provider) {
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
        }


        public double getLatitude(){
            if(location != null){
                latitude = location.getLatitude();
            }
            // return latitude
            return latitude;
        }

        /**
         * Function to get longitude
         * */
        public double getLongitude(){
            if(location != null){
                longitude = location.getLongitude();
            }
            // return longitude
            return longitude;
        }

        public boolean canGetLocation()
        {
            return this.canGetLocation;
        }



        public List<Address>
        getGeocoderAddress(Context context)
        {
            if(location!=null)
            {
                Geocoder geocoder=new Geocoder(context, Locale.ENGLISH);
                try
                {
                    List<Address>
                            addresses=geocoder.getFromLocation(latitude,longitude, 1);
                    return addresses;
                }
                catch(IOException e)
                {
                    e.printStackTrace();
                    Log.e("ERROR: Geocoder","Impossible to connect to Geocoder",e);
                }

            }
            return null;
        }

        public String getLocality(Context context)
        {
            List<Address>
                    addresses=getGeocoderAddress(context);
            if(addresses!=null && addresses.size()>0)
            {
                Address address=addresses.get(0);
                String city=address.getLocality();
                String adminArea = address.getAdminArea();
                String subLocality = address.getSubLocality();
                System.out.println("The getloc locality " + city);
                System.out.println("The getloc admin area " + adminArea);
                System.out.println("The getloc sublocality " + subLocality);

                if(city == null) city = adminArea;
                if(city == null) city = subLocality;
                return city;
            }
            else
            {
                return null;
            }
        }



}
