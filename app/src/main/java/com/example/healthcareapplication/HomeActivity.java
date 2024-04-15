package com.example.healthcareapplication;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;
import com.facebook.AccessToken;
import com.facebook.login.LoginManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class HomeActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        SharedPreferences sharedpreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
        String username = sharedpreferences.getString("username","").toString();
//        Toast.makeText(getApplicationContext(),"Welcome " + username, Toast.LENGTH_SHORT).show();
        
        CardView exit = findViewById(R.id.cardExit);
        exit.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);
            builder.setTitle("Logout");
            builder.setMessage("Are you sure you want to logout?");
            builder.setPositiveButton("Yes", (dialog, which) -> {
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.clear();
                editor.apply();
                startActivity(new Intent(HomeActivity.this, LoginActivity.class));
                //signout google
                signOutGG();
                //signout facebook
                signOutFB();
            });
            builder.setNegativeButton("No", (dialog, which) -> {
                dialog.dismiss();
            });
            builder.show();
        });


        CardView finDoctor = findViewById(R.id.cardFindDoctor);
        finDoctor.setOnClickListener(v -> startActivity(new Intent(HomeActivity.this, FindDoctorActivity.class)));

        CardView labTest = findViewById(R.id.cardLabTest);
        labTest.setOnClickListener(v -> startActivity(new Intent(HomeActivity.this, LabTestActivity.class)));

        CardView orderDetail = findViewById(R.id.cardOrderDetails);
        orderDetail.setOnClickListener(v -> startActivity(new Intent(HomeActivity.this, OrderDetailsActivity.class)));

        CardView buyMedicine = findViewById(R.id.cardBuyMedicine);

        buyMedicine.setOnClickListener(v -> startActivity(new Intent(HomeActivity.this, BuyMedicineActivity.class)));

        CardView health = findViewById(R.id.cardHealthDoctor);
        health.setOnClickListener(v -> startActivity(new Intent(HomeActivity.this, MainActivity.class)));
    }
    private void signOutGG() {
        // Đăng xuất khỏi Google
        mAuth.signOut();
        Toast.makeText(this, "Đăng xuất google thành công", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(HomeActivity.this, LoginActivity.class));
        finish();
    }

    private void signOutFB() {
        // Đăng xuất khỏi Facebook
        if (AccessToken.getCurrentAccessToken() != null) {
            LoginManager.getInstance().logOut();
            Toast.makeText(this, "Đăng xuất khỏi Facebook thành công", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(HomeActivity.this, LoginActivity.class));
            finish();
        } else {
            Toast.makeText(this, "Bạn chưa đăng nhập bằng Facebook", Toast.LENGTH_SHORT).show();
        }
    }
}