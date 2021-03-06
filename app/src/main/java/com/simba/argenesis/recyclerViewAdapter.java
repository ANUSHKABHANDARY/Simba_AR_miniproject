package com.simba.argenesis;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class recyclerViewAdapter extends RecyclerView.Adapter<recyclerViewAdapter.MyViewHolder> {

    Context context;
    ArrayList<Models> modelsArrayList;
    Model_Info model_info = new Model_Info();

    Models models = new Models();

    int ViewHolderType;

    public recyclerViewAdapter(Context context, ArrayList<Models> modelsArrayList, int viewHolderType) {
        this.context = context;
        this.modelsArrayList = modelsArrayList;
        this.ViewHolderType = viewHolderType;
    }


    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        
        View view;

        switch (ViewHolderType) {
            case 1:
                view = LayoutInflater.from(context).inflate(R.layout.card_home, parent, false);
                break;

            case 2:
                view = LayoutInflater.from(context).inflate(R.layout.card_subcat, parent, false);
                break;

            default:
                throw new IllegalStateException("Unexpected value: " + ViewHolderType);
        }
        Log.d("TAG", "ViewHolderType = "+ViewHolderType);
        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Models models = modelsArrayList.get(position);
        holder.Model_Name.setText(models.Model_Name);
        holder.Model_Image.setImageURI(Uri.parse(models.Model_Image));
        String UID = models.getModel_Uid();
        Glide.with(context)
                .load(models.Model_Image)
                .into(holder.Model_Image);

        holder.Model_Image.setOnClickListener(view -> {
            Models model = modelsArrayList.get(position);
            Intent intent = new Intent(view.getContext(), Model_Info.class);
            intent.putExtra("Model", UID);
            Log.d("Testing", UID);
            context.startActivity(intent);

        });

        holder.view_Model.setOnClickListener(view -> {
            Intent intent = new Intent(view.getContext(), viewModel.class);
            intent.putExtra("Model", UID);
            Log.d("Testing", UID);
            context.startActivity(intent);
        });


    }


    @Override
    public int getItemCount() {
        return modelsArrayList.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView Model_Name;
        ImageView Model_Image;
        CardView view_Model;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            Model_Name = itemView.findViewById(R.id.modelNameRecyclerView);
            Model_Image = itemView.findViewById(R.id.modelImageRecyclerView);
            view_Model = itemView.findViewById(R.id.viewModelRecyclerView);

        }
    }

}