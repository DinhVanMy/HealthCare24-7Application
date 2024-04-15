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
import android.widget.Toast;

import java.util.regex.Pattern;

public class BuyMedicineBookActivity extends AppCompatActivity {
    EditText edname, edaddress, edcontact, edpincode;
    Button btnBooking;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_medicine_book);
        edname = findViewById(R.id.editTextBMBFullName);
        edaddress = findViewById(R.id.editTextBMBAddress);
        edcontact = findViewById(R.id.editTextBMBContact);
        edpincode = findViewById(R.id.editTextBMBPinCode);

        btnBooking = findViewById(R.id.buttonBMBBooking);

        Intent intent = getIntent();
        String[] price = intent.getStringExtra("price").toString().split(Pattern.quote(":"));
        String date = intent.getStringExtra("date");

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        btnBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isFormValid()) {
                    SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                    String username = sharedPreferences.getString("username", "").toString();
                    Database db = new Database(getApplicationContext(), "healthcare", null, 1);
                    db.addOrder(username, edname.getText().toString(),edaddress.getText().toString(),edcontact.getText().toString(), Integer.parseInt(edpincode.getText().toString()), date.toString(),"", Float.parseFloat(price[1].toString()), "medicine");
                    db.removeCart(username,"medicine");
                    builder.setTitle("Notification");
                    builder.setMessage("Your booking is done successfully");
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
                    startActivity(new Intent(BuyMedicineBookActivity.this, HomeActivity.class));
                } else {
                    builder.setTitle("Notification");
                    builder.setMessage("Please fill in all fields");
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
                }
            }
        });
    }

    private boolean isFormValid() {
        return !edname.getText().toString().isEmpty() &&
                !edaddress.getText().toString().isEmpty() &&
                !edcontact.getText().toString().isEmpty() &&
                !edpincode.getText().toString().isEmpty();
    }

}