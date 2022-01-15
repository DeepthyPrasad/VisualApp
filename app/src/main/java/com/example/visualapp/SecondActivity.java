package com.example.visualapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

public class SecondActivity extends MainActivity {

    String[] spinnerHeatMapRentNames={"Apartment Type","Regions","Developer"};
    String[] spinnerHeatMapDwellNames={"Apartment Type","Regions","Developer"};
    private DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);

        // creating a new db handler class and passing our context to it.
        try {
            dbHandler = new DBHandler(SecondActivity.this);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Add options to fit the image in the app screen
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inDensity =  DisplayMetrics.DENSITY_DEFAULT;

        ImageView imageView=(ImageView) findViewById(R.id.imageView);
        ArrayList<StoreData> imageDb = dbHandler.readImages("pairplot");
        byte[] byt1 = imageDb.get(0).getImage();
        Bitmap bm1 = BitmapFactory.decodeByteArray(byt1, 0 ,byt1.length, options);
        imageView.setImageBitmap(bm1);

        //Display the blob image after converting to byte array
        ImageView imageView1=(ImageView) findViewById(R.id.imageView1);
        // Reading the blob image from sqllite with a matching image_desc
        ArrayList<StoreData> imagesFromDb = dbHandler.readImages("pca");
        byte[] byteArray = imagesFromDb.get(0).getImage();
        Bitmap bm = BitmapFactory.decodeByteArray(byteArray, 0 ,byteArray.length, options);
        imageView1.setImageBitmap(bm);

        ImageView imageView2=(ImageView) findViewById(R.id.imageView2);
        ArrayList<StoreData> imagesDb = dbHandler.readImages("dfprinci");
        byte[] byt = imagesDb.get(0).getImage();
        Bitmap bmp = BitmapFactory.decodeByteArray(byt, 0 ,byt.length, options);
        imageView2.setImageBitmap(bmp);

        //Spinner for Rent
        Spinner spinHeatMapRent = (Spinner) findViewById(R.id.spinnerHeatMapRent);
        spinHeatMapRent.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
                ImageView imageView2 = (ImageView) findViewById(R.id.imageViewHeatMapRent);

                if(spinnerHeatMapRentNames[position].equals("Apartment Type")) {

                    ArrayList<StoreData> imagesFromDb = dbHandler.readImages("heattypeyearrent");
                    byte[] byteArray = imagesFromDb.get(0).getImage();
                    Bitmap bm = BitmapFactory.decodeByteArray(byteArray, 0 ,byteArray.length, options);
                    imageView2.setImageBitmap(bm);
                } else if(spinnerHeatMapRentNames[position].equals("Regions")) {

                    ArrayList<StoreData> imagesFromDb = dbHandler.readImages("heatlocyearrent");
                    byte[] byteArray = imagesFromDb.get(0).getImage();
                    Bitmap bm = BitmapFactory.decodeByteArray(byteArray, 0 ,byteArray.length, options);
                    imageView2.setImageBitmap(bm);
                } else if(spinnerHeatMapRentNames[position].equals("Developer")) {

                    ArrayList<StoreData> imagesFromDb = dbHandler.readImages("heatdevyearrent");
                    byte[] byteArray = imagesFromDb.get(0).getImage();
                    Bitmap bm = BitmapFactory.decodeByteArray(byteArray, 0 ,byteArray.length, options);
                    imageView2.setImageBitmap(bm);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });

        ArrayAdapter adapterRent = new ArrayAdapter(this,android.R.layout.simple_spinner_item,spinnerHeatMapRentNames);
        adapterRent.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinHeatMapRent.setAdapter(adapterRent);

        // Spinner for Dwellings
        Spinner spinHeatMapDwell = (Spinner) findViewById(R.id.spinnerHeatMapDwell);
        spinHeatMapDwell.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {

                if(spinnerHeatMapDwellNames[position].equals("Apartment Type")) {
                    ImageView imageView3 = (ImageView) findViewById(R.id.imageViewHeatMapDwell);
                    ArrayList<StoreData> imagesFromDb = dbHandler.readImages("heattypeyeardwel");
                    byte[] byteArray = imagesFromDb.get(0).getImage();
                    Bitmap bm = BitmapFactory.decodeByteArray(byteArray, 0 ,byteArray.length, options);
                    imageView3.setImageBitmap(bm);
                } else if(spinnerHeatMapDwellNames[position].equals("Regions")) {
                    ImageView imageView3 = (ImageView) findViewById(R.id.imageViewHeatMapDwell);
                    ArrayList<StoreData> imagesFromDb = dbHandler.readImages("heatlocyeardwel");
                    byte[] byteArray = imagesFromDb.get(0).getImage();
                    Bitmap bm = BitmapFactory.decodeByteArray(byteArray, 0 ,byteArray.length, options);
                    imageView3.setImageBitmap(bm);
                } else if(spinnerHeatMapDwellNames[position].equals("Developer")) {
                    ImageView imageView3 = (ImageView) findViewById(R.id.imageViewHeatMapDwell);
                    ArrayList<StoreData> imagesFromDb = dbHandler.readImages("heatdevyeardwel");
                    byte[] byteArray = imagesFromDb.get(0).getImage();
                    Bitmap bm = BitmapFactory.decodeByteArray(byteArray, 0 ,byteArray.length, options);
                    imageView3.setImageBitmap(bm);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });

        ArrayAdapter adapterDwell = new ArrayAdapter(this,android.R.layout.simple_spinner_item,spinnerHeatMapDwellNames);
        adapterDwell.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinHeatMapDwell.setAdapter(adapterDwell);

        Button locBtn = (Button) findViewById(R.id.locBtn);
        Button yearBtn= (Button) findViewById(R.id.yearBtn);
        Button devBtn = (Button) findViewById(R.id.devBtn);
        Button typeBtn = (Button) findViewById(R.id.typeBtn);

        locBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(SecondActivity.this, LocationActivity.class);
                startActivity(intent);
            }
        });
        yearBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(SecondActivity.this, YearActivity.class);
                startActivity(intent);
            }
        });
        devBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(SecondActivity.this, DeveloperActivity.class);
                startActivity(intent);
            }
        });
        typeBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(SecondActivity.this, ApartmentTypeActivity.class);
                startActivity(intent);
            }
        });
    }
}
