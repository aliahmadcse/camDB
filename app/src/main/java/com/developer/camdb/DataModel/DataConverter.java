package com.developer.camdb.DataModel;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;


public class DataConverter {

    public static byte[] convertBitmapToByteArray(Bitmap bitmap){
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,stream);
        return stream.toByteArray();
    }

    public static Bitmap convertByteArrayToBitMap(byte[] array){
        return BitmapFactory.decodeStream(new ByteArrayInputStream(array));
//        return BitmapFactory.decodeByteArray(array,0,array.length);
    }
}
