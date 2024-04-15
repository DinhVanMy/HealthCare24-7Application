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

public class BuyMedicineDetailsActivity extends AppCompatActivity {

    TextView tvPackageName, tvTotalCost;
    EditText edDetails;

    Button btnBack, btnAddToCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_medicine_details);

        tvPackageName = findViewById(R.id.textViewBMDPackageName);
        tvTotalCost = findViewById(R.id.textViewBMDTotalCost);
        edDetails = findViewById(R.id.editTextBMDTextMultiLine);
        edDetails.setKeyListener(null);
        btnBack = findViewById(R.id.buttonBMDGoBack);
        btnAddToCart = findViewById(R.id.buttonBMDAddToCart);

        Intent intent =getIntent();
        tvPackageName.setText(intent.getStringExtra("text1"));
        edDetails.setText(intent.getStringExtra("text2"));
        tvTotalCost.setText("Total Cost : "+ intent.getStringExtra("text3") + "$");

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BuyMedicineDetailsActivity.this, BuyMedicineActivity.class));

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
                    db.addCart(username,product,price,"medicine");
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
                    startActivity(new Intent(BuyMedicineDetailsActivity.this, BuyMedicineActivity.class));
                }
            }
        });

    }
}