package com.developer.camdb;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.developer.camdb.DataModel.DataConverter;
import com.developer.camdb.DataModel.Image;
import com.developer.camdb.DataModel.ImageDAO;
import com.developer.camdb.DataModel.ImageDatabase;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    final static int CAMERA_REQUEST_CODE = 51;
    final static int LOCATION_ACCESS_CODE = 52;
    ImageView imageView;
    Bitmap bmpImage;
    Location location;
    FusedLocationProviderClient fusedLocationProviderClient;

    ImageDAO imageDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.capturedImageView);
        bmpImage = null;
        imageDAO = ImageDatabase.getDBInstance(this).imageDAO();
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == LOCATION_ACCESS_CODE && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivityForResult(intent, CAMERA_REQUEST_CODE);
            }
        } else {
            Toast.makeText(this, "Permission Not granted", Toast.LENGTH_SHORT).show();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void captureImage(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (getApplicationContext().checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_ACCESS_CODE);
            }
        }

        if (getApplicationContext().checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivityForResult(intent, CAMERA_REQUEST_CODE);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case CAMERA_REQUEST_CODE:
                if (resultCode == Activity.RESULT_OK) {
                    bmpImage = (Bitmap) data.getExtras().get("data");
                    if (bmpImage != null) {
                        imageView.setImageBitmap(bmpImage);
                    }
                }
                break;
        }
    }

    public void saveImage(View view) {
        if (bmpImage == null) {
            Toast.makeText(
                    this, "No Image Provided", Toast.LENGTH_SHORT
            ).show();
        } else {
//            accessing user location
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }

            Image image = new Image();
            fusedLocationProviderClient.getLastLocation()
                    .addOnSuccessListener(new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            image.setImage(DataConverter.convertBitmapToByteArray(bmpImage));
                            Geocoder geocoder;
                            List<Address> addresses;
                            geocoder = new Geocoder(MainActivity.this, Locale.getDefault());
                            String address = "";
                            try {
                                addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                                address = addresses.get(0).getAddressLine(0);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }


                            image.setLocation(address);
                            image.setLatitute(location.getLatitude());
                            image.setLongitude(location.getLongitude());
                            imageDAO.insertImage(image);
                            Toast.makeText(MainActivity.this,"Image saved success",Toast.LENGTH_SHORT).show();
                        }
                    });

//            Toast.makeText(this,"Image saved success",Toast.LENGTH_SHORT).show();
        }
    }

    public void showRecord(View view) {
        Intent intent = new Intent(this,ShowImgesActivity.class);
        startActivity(intent);
    }
}
