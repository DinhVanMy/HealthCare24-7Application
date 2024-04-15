package com.example.healthcareapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.healthcareapplication.adapter.LabTestAdapter;

public class LabTestActivity extends AppCompatActivity {

    private final String[][] packages =
            {
                    {"Package 1 : Full Body Checkup", "", "", "", "999"},
                    {"Package 2 : Blood Glucose Fasting", "", "", "", "299"},
                    {"Package 3 : COVID-19 Antibody", "", "", "", "899"},
                    {"Package 4 : Thyroid Check", "", "", "", "499"},
                    {"Package 5 : Immunity Check", "", "", "", "999"},

            };

    private String[] package_details = {
            "Blood Glucose Fasting\n" + " Complete Hemogram\n" + "HbA1c\n"+" Iron Studies\n"+"Kidney Function Test\n" + "LDH Lactate Dehydrogenase, Serum\n" + "Lipid Profile\n" + "Liver Fuction Test",
            "Blood Glucose Fasting",
            "COVID-19 Antibody - IgG",
            "Thyroid Profile-Total (T3, T4 & TSH Ultra-sensitive)",
            "Complete Hemogram\n" + "CRP (C Reactive Protein) Quantitative, Serum\n" +" Iron Studies\n"+"Kidney Function Test\n" + "Vitamin D Total-25 Hydroxy\n" + "Liver Fuction Test\n" + "Lipid Profile"
    };


    private RecyclerView recyclerView;
    private Button btnGoToCart, btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_test);

        recyclerView = findViewById(R.id.recycle);
        btnGoToCart = findViewById(R.id.buttonLTAddToCart);
        btnBack = findViewById(R.id.buttonLTGoBack);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        LabTestAdapter adapter = new LabTestAdapter(packages, package_details);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new LabTestAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

                Intent intent = new Intent(LabTestActivity.this, LabTestDetailsActivity.class);
                intent.putExtra("text1", packages[position][0]);
                intent.putExtra("text2", package_details[position]);
                intent.putExtra("text3", packages[position][4]);
                startActivity(intent);
            }
        });

        // Button click listeners
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LabTestActivity.this, HomeActivity.class));
            }
        });
        btnGoToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LabTestActivity.this, CartLabActivity.class));
            }
        });
    }}