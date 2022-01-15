package com.example.visualapp;

import java.sql.Blob;

public class StoreData {

        // variables for our image_id, image_desc and image.
        private int imageID;
        private String imageDescription;
        private byte[] image;

        // creating getter and setter methods
        public int getImageID() {
            return imageID;
        }

        public void setImageID(int imageID) {
            this.imageID = imageID;
        }

        public String getImageDescription() {
            return imageDescription;
        }

        public void setImageDescription(String imageDescription) {
            this.imageDescription = imageDescription;
        }

        public byte[] getImage() {
            return image;
        }

        // constructor
        public StoreData(int imageID, String imageDescription, byte[] image) {
            this.imageID = imageID;
            this.imageDescription = imageDescription;
            this.image = image;
        }
    }
