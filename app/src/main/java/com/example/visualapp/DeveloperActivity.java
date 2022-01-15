package com.example.visualapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import java.io.IOException;
import java.util.ArrayList;

public class DeveloperActivity extends SecondActivity implements AdapterView.OnItemSelectedListener{

    String[] spinnerDevNames={"Select an option","Rent as PieChart","Rent as BarChart",
            "Rent as LineChart","Dwellings as PieChart",
            "Dwellings as BarChart", "Dwellings as LineChart"};
    private DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.developer_activity);

        // creating a new db handler class and passing our context to it.
        try {
            dbHandler = new DBHandler(DeveloperActivity.this);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Spinner spinDev = (Spinner) findViewById(R.id.spinnerDev);
        spinDev.setOnItemSelectedListener(this);

        //Creating the ArrayAdapter instance having the list
        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,spinnerDevNames);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinDev.setAdapter(adapter);
    }
    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {

        // Add options to fit the image in the app screen
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inDensity =  DisplayMetrics.DENSITY_DEFAULT;

        ImageView imageViewDev = (ImageView) findViewById(R.id.imageViewDev);

        if(spinnerDevNames[position].equals("Select an option")) {
            ArrayList<StoreData> imagesFromDb = dbHandler.readImages("emptyimage");
            System.out.println("The imagesFromDb " + imagesFromDb);
            byte[] byteArray = imagesFromDb.get(0).getImage();
            Bitmap bm = BitmapFactory.decodeByteArray(byteArray, 0 ,byteArray.length, options);
            imageViewDev.setImageBitmap(bm);
        } else if(spinnerDevNames[position].equals("Rent as PieChart")) {
            ArrayList<StoreData> imagesFromDb = dbHandler.readImages("rentdevpie");
            System.out.println("The imagesFromDb " + imagesFromDb);
            byte[] byteArray = imagesFromDb.get(0).getImage();
            Bitmap bm = BitmapFactory.decodeByteArray(byteArray, 0 ,byteArray.length, options);
            imageViewDev.setImageBitmap(bm);
        } else if(spinnerDevNames[position].equals("Rent as BarChart")) {
            ArrayList<StoreData> imagesFromDb = dbHandler.readImages("rentdevbar");
            System.out.println("The imagesFromDb " + imagesFromDb);
            byte[] byteArray = imagesFromDb.get(0).getImage();
            Bitmap bm = BitmapFactory.decodeByteArray(byteArray, 0 ,byteArray.length);
            imageViewDev.setImageBitmap(bm);
        } else if(spinnerDevNames[position].equals("Rent as LineChart")) {
            ArrayList<StoreData> imagesFromDb = dbHandler.readImages("rentdevline");
            System.out.println("The imagesFromDb " + imagesFromDb);
            byte[] byteArray = imagesFromDb.get(0).getImage();
            Bitmap bm = BitmapFactory.decodeByteArray(byteArray, 0 ,byteArray.length, options);
            imageViewDev.setImageBitmap(bm);
        } else if(spinnerDevNames[position].equals("Dwellings as PieChart")) {
            ArrayList<StoreData> imagesFromDb = dbHandler.readImages("dwelldevpie");
            System.out.println("The imagesFromDb " + imagesFromDb);
            byte[] byteArray = imagesFromDb.get(0).getImage();
            Bitmap bm = BitmapFactory.decodeByteArray(byteArray, 0 ,byteArray.length, options);
            imageViewDev.setImageBitmap(bm);
        } else if(spinnerDevNames[position].equals("Dwellings as BarChart")) {
            ArrayList<StoreData> imagesFromDb = dbHandler.readImages("dwelldevbar");
            System.out.println("The imagesFromDb " + imagesFromDb);
            byte[] byteArray = imagesFromDb.get(0).getImage();
            Bitmap bm = BitmapFactory.decodeByteArray(byteArray, 0 ,byteArray.length, options);
            imageViewDev.setImageBitmap(bm);
        } else if(spinnerDevNames[position].equals("Dwellings as LineChart")) {
            ArrayList<StoreData> imagesFromDb = dbHandler.readImages("dwelldevline");
            System.out.println("The imagesFromDb " + imagesFromDb);
            byte[] byteArray = imagesFromDb.get(0).getImage();
            Bitmap bm = BitmapFactory.decodeByteArray(byteArray, 0 ,byteArray.length, options);
            imageViewDev.setImageBitmap(bm);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub

    }
}


