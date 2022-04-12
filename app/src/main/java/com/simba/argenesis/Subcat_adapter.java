package com.simba.argenesis;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Subcat_adapter extends RecyclerView.Adapter<Subcat_adapter.Viewholder> {

    Subcat_models[] subcatmodels;
    Context context;

    public Subcat_adapter(Subcat_models[] modelData, Subcategories activity) {
        this.subcatmodels = modelData;
        this.context = activity;
    }

    @NonNull
    @Override
    public Subcat_adapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.subcat_carditem,parent,false);
        Viewholder viewholder = new Viewholder(view);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull Subcat_adapter.Viewholder holder, int position) {
        final Subcat_models modelDataList = subcatmodels[position];

        holder.modelName.setText(modelDataList.getModel_name());
        holder.modelImage.setImageResource(modelDataList.getModel_img());
        holder.bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,Model_Info.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return subcatmodels.length;
    }

    public class Viewholder extends RecyclerView.ViewHolder{
        ImageView modelImage;
        TextView modelName;
        Button bt;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            modelImage = itemView.findViewById(R.id.img1);
            modelName = itemView.findViewById(R.id.text1);
            bt = itemView.findViewById(R.id.bt1);
        }
    }
}
