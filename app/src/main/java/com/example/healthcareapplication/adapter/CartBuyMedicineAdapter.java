package com.example.healthcareapplication.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.healthcareapplication.R;

import java.util.ArrayList;

public class CartBuyMedicineAdapter extends RecyclerView.Adapter<CartBuyMedicineAdapter.ViewHolder>{
    private ArrayList<String> dbData;

    private String[] dataArr;
    public CartBuyMedicineAdapter(ArrayList<String> dbData) {
        this.dbData = dbData;
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
    @NonNull
    @Override
    public CartBuyMedicineAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.multi_lines, parent, false);
        return new CartBuyMedicineAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartBuyMedicineAdapter.ViewHolder holder, int position) {
        String data = dbData.get(position);
        dataArr = data.split("\\$");
        holder.line1.setText(dataArr[0]);
        holder.line5.setText("Total Cost: " + dataArr[1] + "$");
        holder.line2.setText("");
        holder.line3.setText("");
        holder.line4.setText("");

    }

    @Override
    public int getItemCount() {
        return dbData.size();
    }
}
