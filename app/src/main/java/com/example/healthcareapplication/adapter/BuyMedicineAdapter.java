package com.example.healthcareapplication.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.healthcareapplication.R;

public class BuyMedicineAdapter extends RecyclerView.Adapter<BuyMedicineAdapter.ViewHolder> {
    private final String[][] packages;

    public BuyMedicineAdapter(String[][] packages, String[] packageDetails) {
        this.packages = packages;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    private BuyMedicineAdapter.OnItemClickListener listener;

    public void setOnItemClickListener(BuyMedicineAdapter.OnItemClickListener listener) {
        this.listener = listener;
    }


    @NonNull
    public BuyMedicineAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.multi_lines, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BuyMedicineAdapter.ViewHolder holder, final int position) {
        holder.line1.setText(packages[position][0]);
        holder.line2.setText(packages[position][1]);
        holder.line3.setText(packages[position][2]);
        holder.line4.setText(packages[position][3]);
        holder.line5.setText("Total Cost: " + packages[position][4] + "$");

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onItemClick(holder.getAdapterPosition());
                }
            }
        });
    }



    @Override
    public int getItemCount() {
        return packages.length;
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
