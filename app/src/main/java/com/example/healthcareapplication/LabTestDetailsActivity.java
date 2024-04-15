package com.example.healthcareapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LabTestDetailsActivity extends AppCompatActivity {
    TextView tvPackageName, tvTotalCost;
    EditText edDetails;

    Button btnAddToCart, btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_test_details);

        tvPackageName = findViewById(R.id.textViewLDPackageName);
        tvTotalCost = findViewById(R.id.textViewLDTotalCost);
        edDetails = findViewById(R.id.editTextLDTextMultiLine);
        btnAddToCart = findViewById(R.id.buttonLDAddToCart);
        btnBack = findViewById(R.id.buttonLDGoBack);

        edDetails.setKeyListener(null);
        Intent intent = getIntent();
        tvPackageName.setText(intent.getStringExtra("text1"));
        edDetails.setText(intent.getStringExtra("text2"));
        tvTotalCost.setText("Total Cost: " + intent.getStringExtra("text3") + "$");

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LabTestDetailsActivity.this, LabTestActivity.class));
            }
        });

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        btnAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                String username = sharedPreferences.getString("username", "").toString();
                String product = tvPackageName.getText().toString();
                float price = Float.parseFloat(intent.getStringExtra("text3").toString());

                Database db = new Database(getApplicationContext(), "healthcare", null,1);

                if(db.checkCart(username,product) == 1) {
                    builder.setTitle("Notification");
                    builder.setMessage("Product Already Added");
                    final AlertDialog dialog = builder.create();
                    dialog.show();

                    new android.os.Handler().postDelayed(
                            new Runnable() {
                                public void run() {
                                    dialog.dismiss();
                                }
                            },
                            2000
                    );
                    Drawable drawable = getResources().getDrawable(R.drawable.rounded_alertdialog_bg);
                    dialog.getWindow().setBackgroundDrawable(drawable);
                } else {
                    db.addCart(username,product,price,"lab");
                    builder.setTitle("Notification");
                    builder.setMessage("Record Inserted to Cart");
                    final AlertDialog dialog = builder.create();
                    dialog.show();

                    new android.os.Handler().postDelayed(
                            new Runnable() {
                                public void run() {
                                    dialog.dismiss();
                                }
                            },
                            2000
                    );
                    Drawable drawable = getResources().getDrawable(R.drawable.rounded_alertdialog_bg);
                    dialog.getWindow().setBackgroundDrawable(drawable);
                    startActivity(new Intent(LabTestDetailsActivity.this, LabTestActivity.class));
                }
            }
        });


    }
}