package com.simba.argenesis;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class recyclerViewAdapter extends RecyclerView.Adapter<recyclerViewAdapter.MyViewHolder> {

    Context context;
    ArrayList<Models> modelsArrayList;

    public recyclerViewAdapter(Context context, ArrayList<Models> modelsArrayList) {
        this.context = context;
        this.modelsArrayList = modelsArrayList;
    }

    @NonNull
    @Override
    public recyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.card_home, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Models models = modelsArrayList.get(position);
        holder.Model_Name.setText(models.Model_Name);
//        holder.Model_Image.setImageURI();

    }

//    @Override
//    public void onBindViewHolder(@NonNull HomePage.MyAdapter.MyViewHolder holder, int position) {
//
//    }

    @Override
    public int getItemCount() {
        return modelsArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView Model_Name;
        ImageView Model_Image;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            Model_Name = itemView.findViewById(R.id.modelNameRecyclerView);
            Model_Image = itemView.findViewById(R.id.modelImageRecyclerView);
        }
    }
}