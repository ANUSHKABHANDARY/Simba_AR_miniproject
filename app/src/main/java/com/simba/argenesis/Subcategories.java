package com.simba.argenesis;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Subcategories extends AppCompatActivity {

    private ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subcategories);
        back = findViewById(R.id.back);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Subcat_models[] subcatmodels = new Subcat_models[]{
                new Subcat_models("Earth",R.drawable.earth),
                new Subcat_models("Mars",R.drawable.mars),
                new Subcat_models("Mercury",R.drawable.mercury),
                new Subcat_models("Earth",R.drawable.earth),
                new Subcat_models("Mars",R.drawable.mars),
                new Subcat_models("Mercury",R.drawable.mercury),
                new Subcat_models("Earth",R.drawable.earth),
                new Subcat_models("Mars",R.drawable.mars),
                new Subcat_models("Mercury",R.drawable.mercury),
                new Subcat_models("Earth",R.drawable.earth),
                new Subcat_models("Mars",R.drawable.mars),
                new Subcat_models("Mercury",R.drawable.mercury),

        };
        Subcat_adapter subcat_adapter = new Subcat_adapter(subcatmodels,Subcategories.this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2,GridLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(subcat_adapter);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Subcategories.this,All_Categories.class);
                startActivity(intent);
            }
        });
    }
}