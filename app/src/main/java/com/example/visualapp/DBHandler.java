package com.example.visualapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class DBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DB_DIR = "/data/data/"+BuildConfig.APPLICATION_ID+"/databases/";
    public static final String DATABASE_NAME = "SQLite_Python.db";
    public SQLiteDatabase myDataBase;

    public static final String TABLE_NAME = "SqliteDb_images";
    public static final String IMAGE_ID_COL = "image_id";
    public static final String IMAGE_DESC_COL = "image_desc";
    public static final String IMAGE_COL = "image";
    private static Context myContext;

    // creating a constructor for our database handler.
    public DBHandler(Context context) throws IOException {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        myContext = context;

        boolean dbexist = checkdatabase();
        if (dbexist) {
            System.out.println("Database exists");
            opendatabase();
        } else {
            System.out.println("Database doesn't exist");
            createdatabase();
        }
    }


    public void createdatabase() throws IOException {
        boolean dbexist = checkdatabase();
        if(dbexist) {
            System.out.println(" Database exists.");
        } else {
            this.getReadableDatabase();
            try {
                copydatabase();
            } catch(IOException e) {
                throw new Error("Error copying database");
            }
        }
    }

    private boolean checkdatabase() {

        boolean checkdb = false;
        try {
            String myPath = DB_DIR + DATABASE_NAME;
            File dbfile = new File(myPath);
            checkdb = dbfile.exists();
        } catch(SQLiteException e) {
            System.out.println("Database doesn't exist");
        }
        return checkdb;
    }

    private void copydatabase() throws IOException {
        //Open your local db as the input stream
        InputStream myinput = myContext.getAssets().open(DATABASE_NAME);

        // Path to the just created empty db
        String outfilename = DB_DIR + DATABASE_NAME;

        //Open the empty db as the output stream
        OutputStream myoutput = new FileOutputStream(outfilename);

        // transfer byte to inputfile to outputfile
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myinput.read(buffer))>0) {
            myoutput.write(buffer,0,length);
        }

        //Close the streams
        myoutput.flush();
        myoutput.close();
        myinput.close();
    }

    public void opendatabase() throws SQLException {
        //Open the database
        String mypath = DB_DIR + DATABASE_NAME;
        myDataBase = SQLiteDatabase.openDatabase(mypath, null, SQLiteDatabase.OPEN_READWRITE);
    }

    public synchronized void close() {
        if(myDataBase != null) {
            myDataBase.close();
        }
        super.close();
    }

    // we have created a new method for reading all the images.
    public ArrayList<StoreData> readImages(String image_desc) {
        // on below line we are creating a database for reading our database.
        SQLiteDatabase db = this.getReadableDatabase();

        // on below line we are creating a cursor with query to read data from database.
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE image_desc = '" + image_desc + "'";
        Log.v("SecondActivity", "Found "+ query);
        Cursor cursorImage = db.rawQuery(query,null);

        // on below line we are creating a new array list.
        ArrayList<StoreData> storeDataArrayList = new ArrayList<>();

        // moving our cursor to first position.
        if (cursorImage.moveToFirst()) {
            do {
                // on below line we are adding the data from cursor to our array list.
                storeDataArrayList.add(new StoreData(cursorImage.getInt(0),
                        cursorImage.getString(1),
                        cursorImage.getBlob(2)));
            } while (cursorImage.moveToNext());
            // moving our cursor to next.
        }
        // at last closing our cursor
        // and returning our array list.
        cursorImage.close();
        return storeDataArrayList;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Do nothing
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Do nothing
    }
}