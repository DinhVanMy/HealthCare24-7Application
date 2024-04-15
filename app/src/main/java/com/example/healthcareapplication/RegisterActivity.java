package com.example.healthcareapplication;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    EditText edUsername, edEmail, edPassword, edComfirm;
    Button btn;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        edUsername = findViewById(R.id.editTextAppFullName);
        edPassword = findViewById(R.id.editTextAppContactNumber);
        edEmail = findViewById(R.id.editTextAppAddress);
        edComfirm = findViewById(R.id.editTextAppFees);
        btn = findViewById(R.id.buttonBookAppointment);
        tv = findViewById(R.id.textViewExistingUser);

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edUsername.getText().toString();
                String email = edEmail.getText().toString();
                String password = edPassword.getText().toString();
                String confirm = edComfirm.getText().toString();
                Database db = new Database(getApplicationContext(),"healthcare", null,1);

                if (username.length() == 0 || email.length() == 0 || password.length() == 0 || confirm.length() == 0) {
                    builder.setTitle("Notification");
                    builder.setMessage("Please fill all detail");
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
                } else if (db.checkUserExist(username) == 1) {
                    builder.setTitle("Thông báo");
                    builder.setMessage("User Exists");
                    // Không cần nút OK, hộp thoại tự đóng sau 3 giây
                    final AlertDialog dialog = builder.create();
                    dialog.show();
                    // Tạo một handler để đóng dialog sau một khoảng thời gian
                    new android.os.Handler().postDelayed(
                            new Runnable() {
                                public void run() {
                                    dialog.dismiss();
                                }
                            },
                            2000
                    );
                } else {
                    if (password.compareTo(confirm) == 0) {
                        if(isValid(password)) {
                            db.register(username,email,password);
                            builder.setTitle("Notification");
                            builder.setMessage("Record Inserted");
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
                            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));

                        } else {
                            builder.setTitle("Notification");
                            builder.setMessage("Password must contain at least 8 characters, having letter, digit and special symbol");
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
                    } else {
                        builder.setTitle("Notification");
                        builder.setMessage("Password and Confirm password didn't match");
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


            }
        });


    }

    public static boolean isValid(String passwordhere) {
        int f1 = 0, f2 = 0, f3 = 0;

        if (passwordhere.length() <= 8) {
            return false;
        } else {
            for (int i = 0; i < passwordhere.length(); i++) {
                if (Character.isLetter(passwordhere.charAt(i))) {
                    f1 = 1;
                }
            }

            for (int j = 0; j < passwordhere.length(); j++) {
                if (Character.isDigit(passwordhere.charAt(j))) {
                    f2 = 1;
                }
            }
            for (int k = 0; k < passwordhere.length(); k++) {
                char c = passwordhere.charAt(k);
                if (c >= 33 && c <= 46 || c == 64) {
                    f3 = 1;
                }
            }

            if (f1 == 1 && f2 == 1 & f3 == 1)
                return true;
            return false;

        }
    }
}