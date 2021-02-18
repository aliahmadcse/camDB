package com.developer.camdb.DataModel;

//import java.util.Date;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Images")
public class Image {
    @PrimaryKey(autoGenerate = true)
    int id;
    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    byte[] image;
    double latitute;
    double longitude;
    String location;

    public void setid(int uid) {
        this.id = uid;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public void setLatitute(double latitute) {
        this.latitute = latitute;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getid() {
        return id;
    }

    public byte[] getImage() {
        return image;
    }

    public double getLatitute() {
        return latitute;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getLocation() {
        return location;
    }


}
