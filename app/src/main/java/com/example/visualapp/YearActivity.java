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

public class YearActivity extends SecondActivity implements AdapterView.OnItemSelectedListener{

    String[] spinnerYearNames={"Select an option","Rent as PieChart","Rent as BarChart",
            "Rent as LineChart","Dwellings as PieChart",
            "Dwellings as BarChart", "Dwellings as LineChart"};
    private DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.year_activity);

        // creating a new db handler class and passing our context to it.
        try {
            dbHandler = new DBHandler(YearActivity.this);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Spinner spinYear = (Spinner) findViewById(R.id.spinnerYear);
        spinYear.setOnItemSelectedListener(this);

        //Creating the ArrayAdapter instance having the list
        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,spinnerYearNames);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinYear.setAdapter(adapter);
    }
    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {

        // Add options to fit the image in the app screen
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inDensity =  DisplayMetrics.DENSITY_DEFAULT;

        ImageView imageViewYear = (ImageView) findViewById(R.id.imageViewYear);

        if(spinnerYearNames[position].equals("Select an option")) {
            ArrayList<StoreData> imagesFromDb = dbHandler.readImages("emptyimage");
            System.out.println("The imagesFromDb " + imagesFromDb);
            byte[] byteArray = imagesFromDb.get(0).getImage();
            Bitmap bm = BitmapFactory.decodeByteArray(byteArray, 0 ,byteArray.length, options);
            imageViewYear.setImageBitmap(bm);
        } else if(spinnerYearNames[position].equals("Rent as PieChart")) {
            ArrayList<StoreData> imagesFromDb = dbHandler.readImages("rentyearpie");
            System.out.println("The imagesFromDb " + imagesFromDb);
            byte[] byteArray = imagesFromDb.get(0).getImage();
            Bitmap bm = BitmapFactory.decodeByteArray(byteArray, 0 ,byteArray.length, options);
            imageViewYear.setImageBitmap(bm);
        } else if(spinnerYearNames[position].equals("Rent as BarChart")) {
            ArrayList<StoreData> imagesFromDb = dbHandler.readImages("rentyearbar");
            System.out.println("The imagesFromDb " + imagesFromDb);
            byte[] byteArray = imagesFromDb.get(0).getImage();
            Bitmap bm = BitmapFactory.decodeByteArray(byteArray, 0 ,byteArray.length,options);
            imageViewYear.setImageBitmap(bm);
        } else if(spinnerYearNames[position].equals("Rent as LineChart")) {
            ArrayList<StoreData> imagesFromDb = dbHandler.readImages("rentyearline");
            System.out.println("The imagesFromDb " + imagesFromDb);
            byte[] byteArray = imagesFromDb.get(0).getImage();
            Bitmap bm = BitmapFactory.decodeByteArray(byteArray, 0 ,byteArray.length);
            imageViewYear.setImageBitmap(bm);
        } else if(spinnerYearNames[position].equals("Dwellings as PieChart")) {
            ArrayList<StoreData> imagesFromDb = dbHandler.readImages("dwellyrpie");
            System.out.println("The imagesFromDb " + imagesFromDb);
            byte[] byteArray = imagesFromDb.get(0).getImage();
            Bitmap bm = BitmapFactory.decodeByteArray(byteArray, 0 ,byteArray.length, options);
            imageViewYear.setImageBitmap(bm);
        } else if(spinnerYearNames[position].equals("Dwellings as BarChart")) {
            ArrayList<StoreData> imagesFromDb = dbHandler.readImages("dwellyrbar");
            System.out.println("The imagesFromDb " + imagesFromDb);
            byte[] byteArray = imagesFromDb.get(0).getImage();
            Bitmap bm = BitmapFactory.decodeByteArray(byteArray, 0 ,byteArray.length, options);
            imageViewYear.setImageBitmap(bm);
        } else if(spinnerYearNames[position].equals("Dwellings as LineChart")) {
            ArrayList<StoreData> imagesFromDb = dbHandler.readImages("dwellyrline");
            System.out.println("The imagesFromDb " + imagesFromDb);
            byte[] byteArray = imagesFromDb.get(0).getImage();
            Bitmap bm = BitmapFactory.decodeByteArray(byteArray, 0 ,byteArray.length, options);
            imageViewYear.setImageBitmap(bm);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub

    }
}

