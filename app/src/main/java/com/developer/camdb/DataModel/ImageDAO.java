package com.developer.camdb.DataModel;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ImageDAO {
//    this interface is a data access object
    @Query("SELECT * FROM Images")
    List<Image> getAllImages();

    @Insert
    void insertImage(Image image);
}
