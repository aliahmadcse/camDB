package com.developer.camdb.DataModel;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(
        entities = Image.class,
        version = 1,
        exportSchema = false
)
public abstract class ImageDatabase extends RoomDatabase{
    private static ImageDatabase imageDB = null;

    public abstract ImageDAO imageDAO();

    public static synchronized ImageDatabase getDBInstance(Context context){
        if (imageDB == null){
            imageDB = Room.databaseBuilder(
                    context.getApplicationContext(),
                    ImageDatabase.class,
                    "image19b2"
            ).allowMainThreadQueries().build();
        }

        return imageDB;
    }

}
