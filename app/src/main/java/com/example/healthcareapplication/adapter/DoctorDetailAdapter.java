package com.example.healthcareapplication.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.healthcareapplication.BookAppointmentActivity;
import com.example.healthcareapplication.R;

public class DoctorDetailAdapter extends RecyclerView.Adapter<DoctorDetailAdapter.ViewHolder>{
    private Context context;
    private String[][] doctorDetails;

    public DoctorDetailAdapter(Context context, String[][] doctorDetails) {
        this.context = context;
        this.doctorDetails = doctorDetails;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.multi_lines, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.line1.setText(doctorDetails[position][0]);
        holder.line2.setText(doctorDetails[position][1]);
        holder.line3.setText(doctorDetails[position][2]);
        holder.line4.setText(doctorDetails[position][3]);
        holder.line5.setText("Cons Fees:" + doctorDetails[position][4] + "$");
    }

    @Override
    public int getItemCount() {
        return doctorDetails.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView line1, line2, line3, line4, line5;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            line1 = itemView.findViewById(R.id.line_a);
            line2 = itemView.findViewById(R.id.line_b);
            line3 = itemView.findViewById(R.id.line_c);
            line4 = itemView.findViewById(R.id.line_d);
            line5 = itemView.findViewById(R.id.line_e);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            if (position != RecyclerView.NO_POSITION) {
                Intent it = new Intent(context, BookAppointmentActivity.class);
                it.putExtra("text1", doctorDetails[position][0]);
                it.putExtra("text2", doctorDetails[position][1]);
                it.putExtra("text3", doctorDetails[position][2]);
                it.putExtra("text4", doctorDetails[position][3]);
                it.putExtra("text5", doctorDetails[position][4]);
                context.startActivity(it);
            }
        }
    }
}