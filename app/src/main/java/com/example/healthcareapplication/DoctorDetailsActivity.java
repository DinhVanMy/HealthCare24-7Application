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
import android.widget.TextView;

import com.example.healthcareapplication.adapter.DoctorDetailAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class DoctorDetailsActivity extends AppCompatActivity {

    private String[][] doctor_details1 =
            {
                    {"Doctor Name: Ajit Saste", "Hospital Address: Pimpri", "Exp : 5yrs", "Mobile No: 9898989898", "600"},

                    {"Doctor Name: Prasad Pawar", "Hospital Address: Nigdi", "Exp : 15yrs", "Mobile No: 7898989898", "900"},

                    {"Doctor Name: Swapnil Kale", "Hospital Address: Pune", "Exp : 8yrs", "Mobile No: 8898989898", "300"},

                    {"Doctor Name: Deapak Deshmukh", "Hospital Address: Chinchwad", "Exp : 6yrs", "Mobile No: 989800000", "500"},

                    {"Doctor Name: Ajit Panda", "Hospital Address: Katraj", "Exp : 7yrs", "Mobile No: 7798989898", "800"}
            };
    private String[][] doctor_details2 =
            {
                    {"Doctor Name: Neelam Patil", "Hospital Address: Pimpri", "Exp : 5yrs", "Mobile No: 9898989898", "600"},

                    {"Doctor Name: Swati Pawar", "Hospital Address: Nigdi", "Exp : 15yrs", "Mobile No: 7898989898", "900"},

                    {"Doctor Name: Monnits Kale", "Hospital Address: Pune", "Exp : 8yrs", "Mobile No: 8898989898", "300"},

                    {"Doctor Name: Mayuri Deshmukh", "Hospital Address: Chinchwad", "Exp : 6yrs", "Mobile No: 989800000", "500"},

                    {"Doctor Name: Minaski Panda", "Hospital Address: Katraj", "Exp : 7yrs", "Mobile No: 7798989898", "800"}
            };
    private String[][] doctor_details3 =
            {
                    {"Doctor Name: Seema Patil", "Hospital Address: Pimpri", "Exp : 4yrs", "Mobile No: 9898989898", "600"},

                    {"Doctor Name: Prasad Pawar", "Hospital Address: Nigdi", "Exp : 5yrs", "Mobile No: 7898989898", "900"},

                    {"Doctor Name: Monish Jain", "Hospital Address: Pune", "Exp : 7yrs", "Mobile No: 8898989898", "300"},

                    {"Doctor Name: Visa Deshmukh", "Hospital Address: Chinchwad", "Exp : 6yrs", "Mobile No: 989800000", "500"},

                    {"Doctor Name: Srika Panda", "Hospital Address: Katraj", "Exp : 7yrs", "Mobile No: 7798989898", "800"}
            };
    private String[][] doctor_details4 =
            {
                    {"Doctor Name: Amol Gawade", "Hospital Address: Pimpri", "Exp : 5yrs", "Mobile No: 9898989898", "600"},

                    {"Doctor Name: Prasad Pawar", "Hospital Address: Nigdi", "Exp : 15yrs", "Mobile No: 7898989898", "900"},

                    {"Doctor Name: Deapask Kale", "Hospital Address: Pune", "Exp : 8yrs", "Mobile No: 8898989898", "300"},

                    {"Doctor Name: Deapak Deshpanda", "Hospital Address: Chinchwad", "Exp : 6yrs", "Mobile No: 989800000", "500"},

                    {"Doctor Name: Asok sing", "Hospital Address: Katraj", "Exp : 7yrs", "Mobile No: 7798989898", "800"}
            };
    private String[][] doctor_details5 =
            {
                    {"Doctor Name: Nilesg Norate", "Hospital Address: Pimpri", "Exp : 5yrs", "Mobile No: 9898989898", "600"},

                    {"Doctor Name: Prasad Pawar", "Hospital Address: Nigdi", "Exp : 15yrs", "Mobile No: 7898989898", "900"},

                    {"Doctor Name: Swapnil Lele", "Hospital Address: Pune", "Exp : 8yrs", "Mobile No: 8898989898", "300"},

                    {"Doctor Name: Deapak Kumar", "Hospital Address: Chinchwad", "Exp : 6yrs", "Mobile No: 989800000", "500"},

                    {"Doctor Name: Ankul Panda", "Hospital Address: Katraj", "Exp : 7yrs", "Mobile No: 7798989898", "800"}
            };
    TextView tv;
    Button btn;

    String[][] doctor_details = {};
    private String[][] doctorDetails;

    private RecyclerView recyclerView;
    ArrayList list;
    HashMap<String, String> item;
    SimpleAdapter sa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_details);
        recyclerView = findViewById(R.id.recycleDoctor);
        tv = findViewById(R.id.textViewLDName);
        btn = findViewById(R.id.buttonDDBack);
        Intent it = getIntent();
        String title = it.getStringExtra("title");
        tv.setText(title);
        doctorDetails = getDoctorDetails(title);
        if (title.compareTo("Family Physicians") == 0) {
            doctorDetails = doctor_details1;
        } else if (title.compareTo("Dietician") == 0) {
            doctorDetails = doctor_details2;
        } else if (title.compareTo("Dentist") == 0) {
            doctorDetails = doctor_details3;
        } else if (title.compareTo("Surgeon") == 0) {
            doctorDetails = doctor_details4;
        } else {
            doctorDetails = doctor_details5;
        }
        DoctorDetailAdapter adapter = new DoctorDetailAdapter(this, doctorDetails);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DoctorDetailsActivity.this, FindDoctorActivity.class));
            }
        });

    }
    private String[][] getDoctorDetails(String title) {
        return new String[][]{};
    }
}