package com.example.healthcareapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;

import java.util.Arrays;
import java.util.Collections;
import java.util.Locale;

public class LoginActivity extends AppCompatActivity {
    EditText edUsername, edPassword;
    Button btn;
    TextView tv;
    Spinner spinner;
    GoogleSignInOptions gso;
    GoogleSignInClient gsc;

    ImageView googleBtn,facebookBtn;
    CallbackManager callbackManager;
    public static final String[] language = {"Select Language", "Vietnamese","English","French"};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edUsername = findViewById(R.id.editTextLoginUserName);
        edPassword = findViewById(R.id.editTextLoginPassword);
        btn = findViewById(R.id.buttonLogin);
        tv = findViewById(R.id.textViewNewUser);
        facebookBtn = findViewById(R.id.facebookBtn);
        googleBtn = findViewById(R.id.googleBtn);

        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(this,gso);

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        if(acct!=null){
            navigateToSecondActivity();
        }



        googleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });

        callbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        // App code
                        startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                        finish();
                    }

                    @Override
                    public void onCancel() {
                        // App code
                    }

                    @Override
                    public void onError(@NonNull FacebookException exception) {
                        // App code
                    }
                });
        facebookBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginManager.getInstance().logInWithReadPermissions(LoginActivity.this, Collections.singletonList("public_profile"));
            }
        });


        AlertDialog.Builder builder = new AlertDialog.Builder(this);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(LoginActivity.this, HomeActivity.class));
               String username = edUsername.getText().toString();
                String password = edPassword.getText().toString();
                Database db = new Database(getApplicationContext(),"healthcare", null,1);
                if(username.length() ==0 || password.length() == 0) {

                    // Thông báo cho anh Lê Minh
                    builder.setTitle("Thông báo");
                    builder.setMessage("You have to type your username and password");
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
                    // Sử dụng drawable resource làm background cho cửa sổ hộp thoại
                    Drawable drawable = getResources().getDrawable(R.drawable.rounded_alertdialog_bg);
                    dialog.getWindow().setBackgroundDrawable(drawable);
                    // Toast.makeText(getApplicationContext(),"Please fill all detail",Toast.LENGTH_SHORT).show();
                } else {

                    if(db.login(username,password) ==1) {
                        builder.setTitle("Notification");
                        builder.setMessage("Login successful");
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
                        // Sử dụng drawable resource làm background cho cửa sổ hộp thoại
                        Drawable drawable = getResources().getDrawable(R.drawable.rounded_alertdialog_bg);
                        dialog.getWindow().setBackgroundDrawable(drawable);
                        SharedPreferences sharedpreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedpreferences.edit();
                        editor.putString("username", username);
                        editor.apply();
                        startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                    } else {
                        builder.setTitle("Notification");
                        builder.setMessage("Invalid Username and Password");
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
                        // Sử dụng drawable resource làm background cho cửa sổ hộp thoại
                        Drawable drawable = getResources().getDrawable(R.drawable.rounded_alertdialog_bg);
                        dialog.getWindow().setBackgroundDrawable(drawable);
                    }

                }

            }
        });

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });
        spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,language);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setSelection(0);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedLang = parent.getItemAtPosition(position).toString();
                if(selectedLang.equals("English")){
                    setLocal(LoginActivity.this,"en");
                    finish();
                    startActivity(getIntent());
                } else if (selectedLang.equals("Vietnamese")) {
                    setLocal(LoginActivity.this,"vi");
                    finish();
                    startActivity(getIntent());
                }
                else if (selectedLang.equals("French")) {
                    setLocal(LoginActivity.this,"fr");
                    finish();
                    startActivity(getIntent());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    public void setLocal(Activity activity, String langCode){
        Locale locale = new Locale(langCode);
        Locale.setDefault(locale);
        Resources resources = activity.getResources();
        Configuration conf = resources.getConfiguration();
        conf.setLocale(locale);
        resources.updateConfiguration(conf, resources.getDisplayMetrics());

    }

    void signIn(){
        Intent signInIntent = gsc.getSignInIntent();
        startActivityForResult(signInIntent,1000);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1000){
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);

            try {
                task.getResult(ApiException.class);
                navigateToSecondActivity();
            } catch (ApiException e) {
                Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        }
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }
    void navigateToSecondActivity(){
        finish();
        Intent intent = new Intent(LoginActivity.this,HomeActivity.class);
        startActivity(intent);
    }
}
