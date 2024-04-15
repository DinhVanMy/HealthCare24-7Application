package com.example.healthcareapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.healthcareapplication.adapter.HealthArticlesAdapter;
import com.example.healthcareapplication.adapter.LabTestAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class HealthArticlesActivity extends AppCompatActivity {
    
    private String[][] health_details = {
            {"Walking Daily", "", "", "", "Click More Details"},
            {"Home care of COVID-19", "","","", "Click More Details"},
            {"Stop Smoking", "", "", "", "Click More Details"},
            {"Menstrual Cramps", "", "", "", "Click More Details"},
            {"Healthy Gut", "", "", "", "Click More Details"},
    };
    
    private int[] images = {
            R.drawable.health1,
            R.drawable.health2,
            R.drawable.health3,
            R.drawable.health4,
            R.drawable.health5
    };
    private RecyclerView recyclerView;
    HashMap<String,String> item;
    ArrayList list;
    SimpleAdapter sa;

    ListView lst;
    
    Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_articles);
        btnBack = findViewById(R.id.buttonHABack);
        recyclerView = findViewById(R.id.recycleHealth);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        HealthArticlesAdapter adapter = new HealthArticlesAdapter(health_details);
        recyclerView.setAdapter(adapter);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HealthArticlesActivity.this, HomeActivity.class));
            }
        });

        adapter.setOnItemClickListener(new HealthArticlesAdapter.OnItemClickListener() {
            @Override
            public void onItemClick( int i) {
                Intent it = new Intent(HealthArticlesActivity.this, HealthArticlesDetailsActivity.class);
                it.putExtra("text1", health_details[i][0]);
                it.putExtra("text2", images[i]);
                startActivity(it);
            }
        });
    }
}