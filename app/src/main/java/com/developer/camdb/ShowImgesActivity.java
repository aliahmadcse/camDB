package com.developer.camdb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.developer.camdb.DataModel.ImageDAO;
import com.developer.camdb.DataModel.ImageDatabase;

public class ShowImgesActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ImageDAO imageDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_imges);

        recyclerView = findViewById(R.id.imageRecyclerView);
        imageDAO = ImageDatabase.getDBInstance(this).imageDAO();

        ImageRecycler imageRecycler = new ImageRecycler(imageDAO.getAllImages());

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(imageRecycler);

    }

}