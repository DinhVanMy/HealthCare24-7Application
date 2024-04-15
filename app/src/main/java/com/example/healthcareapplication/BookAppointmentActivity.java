package com.example.healthcareapplication;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class BookAppointmentActivity extends AppCompatActivity {

    EditText ed1, ed2, ed3, ed4;
    TextView tv;

    private DatePickerDialog datePickerDialog;

    private TimePickerDialog timePickerDialog;
    private Button dateButton;
    private Button timeButton;
    private Button btnBook;

    private int selectedHour, selectedMinute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_appointment);

        tv = findViewById(R.id.textViewAppTitle);
        ed1 = findViewById(R.id.editTextAppFullName);
        ed2 = findViewById(R.id.editTextAppAddress);
        ed3 = findViewById(R.id.editTextAppContactNumber);
        ed4 = findViewById(R.id.editTextAppFees);

        dateButton = findViewById(R.id.buttonAppDate);
        timeButton = findViewById(R.id.buttonAppTime);
        btnBook = findViewById(R.id.buttonBookAppointment);
        Button btnBack = findViewById(R.id.buttonAppBack);

        ed1.setKeyListener(null);
        ed2.setKeyListener(null);
        ed3.setKeyListener(null);
        ed4.setKeyListener(null);

        Intent it = getIntent();
        String title = it.getStringExtra("text1");
        String fullname = it.getStringExtra("text2");
        String address = it.getStringExtra("text3");
        String contact = it.getStringExtra("text4");
        String fees = it.getStringExtra("text5");


        tv.setText(title);
        ed1.setText(fullname);
        ed2.setText(address);
        ed3.setText(contact);
        ed4.setText("Cons Fees: " + fees + "$");

        initDatePicker();

        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog.show();
            }
        });
        initTimePicker();
        timeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timePickerDialog.show();
            }
        });


        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BookAppointmentActivity.this, FindDoctorActivity.class));
            }
        });
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        btnBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Database db = new Database(getApplicationContext(), "healthcare", null, 1);
                SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                String username = sharedPreferences.getString("username", "").toString();

                Calendar appointmentTime = Calendar.getInstance();
                appointmentTime.set(Calendar.YEAR, datePickerDialog.getDatePicker().getYear());
                appointmentTime.set(Calendar.MONTH, datePickerDialog.getDatePicker().getMonth());
                appointmentTime.set(Calendar.DAY_OF_MONTH, datePickerDialog.getDatePicker().getDayOfMonth());
                appointmentTime.set(Calendar.HOUR_OF_DAY, selectedHour);
                appointmentTime.set(Calendar.MINUTE, selectedMinute);

                Calendar reminderTime = (Calendar) appointmentTime.clone();
                reminderTime.add(Calendar.MINUTE, -30);

                scheduleAppointmentReminder(reminderTime);

                if(db.checkAppointmentExists(username, title+ " => " + fullname,address,contact,dateButton.getText().toString(), timeButton.getText().toString()) == 1) {

                    builder.setTitle("Notification");
                    builder.setMessage("Appoinment already booked");
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
                } else {
                    db.addOrder(username, title+ " => " + fullname, address, contact,0,dateButton.getText().toString(), timeButton.getText().toString(),Float.parseFloat(fees),"appointment");
                    builder.setTitle("Notification");
                    builder.setMessage("Your appointment is done successfully");
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
                    startActivity(new Intent(BookAppointmentActivity.this, HomeActivity.class));
                }
            }
        });

    }

    private void initDatePicker() {

        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                month = month + 1;
                dateButton.setText(dayOfMonth + "/" + month + "/" + year);
            }
        };

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_DARK;
        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);
        datePickerDialog.getDatePicker().setMinDate(cal.getTimeInMillis() + 86400000);
    }

    private void initTimePicker() {

        TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                selectedHour = hourOfDay;
                selectedMinute = minute;
                timeButton.setText(String.format("%02d:%02d", hourOfDay, minute));
            }
        };

        Calendar cal = Calendar.getInstance();
        selectedHour = cal.get(Calendar.HOUR_OF_DAY);
        selectedMinute = cal.get(Calendar.MINUTE);

        int style = AlertDialog.THEME_HOLO_LIGHT;
        timePickerDialog = new TimePickerDialog(this, style, timeSetListener, selectedHour, selectedMinute, true);
    }

    @SuppressLint("ScheduleExactAlarm")
    private void scheduleAppointmentReminder(Calendar reminderCalendar) {
        Intent intent = new Intent(getApplicationContext(), AppointmentReminderReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, intent, PendingIntent.FLAG_IMMUTABLE);
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        if (alarmManager != null) {
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, reminderCalendar.getTimeInMillis(), pendingIntent);
        }
    }

}