package com.example.healthcareapplication.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.healthcareapplication.R;

public class HealthArticlesAdapter extends RecyclerView.Adapter<HealthArticlesAdapter.ViewHolder>{
    private final String[][] healthDetails;

    public HealthArticlesAdapter(String[][] healthDetails) {
        this.healthDetails = healthDetails;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    private OnItemClickListener listener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.multi_lines, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.line1.setText(healthDetails[position][0]);
        holder.line2.setText(healthDetails[position][1]);
        holder.line3.setText(healthDetails[position][2]);
        holder.line4.setText(healthDetails[position][3]);
        holder.line5.setText(healthDetails[position][4]);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int adapterPosition = holder.getAdapterPosition();
                if (adapterPosition != RecyclerView.NO_POSITION && listener != null) {
                    listener.onItemClick(adapterPosition);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return healthDetails.length;
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