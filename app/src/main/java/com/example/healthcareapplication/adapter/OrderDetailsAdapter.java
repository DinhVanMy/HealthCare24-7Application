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

import java.util.ArrayList;
import java.util.HashMap;

public class OrderDetailsAdapter extends RecyclerView.Adapter<OrderDetailsAdapter.ViewHolder> {
    private ArrayList<HashMap<String, String>> dataList;

    public OrderDetailsAdapter(ArrayList<HashMap<String, String>> dataList) {
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.multi_lines, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HashMap<String, String> item = dataList.get(position);
        holder.line1.setText(item.get("line1"));
        holder.line2.setText(item.get("line2"));
        holder.line3.setText(item.get("line3"));
        holder.line4.setText(item.get("line4"));
        holder.line5.setText(item.get("line5"));
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView line1, line2, line3, line4, line5;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            line1 = itemView.findViewById(R.id.line_a);
            line2 = itemView.findViewById(R.id.line_b);
            line3 = itemView.findViewById(R.id.line_c);
            line4 = itemView.findViewById(R.id.line_d);
            line5 = itemView.findViewById(R.id.line_e);
        }
    }
}
