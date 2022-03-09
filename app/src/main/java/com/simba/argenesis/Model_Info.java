package com.simba.argenesis;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.Objects;

public class Model_Info extends AppCompatActivity {

    View viewAR;
    ImageButton backButton, favButton, moreButton;
    Toast mToast = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Objects.requireNonNull(this.getSupportActionBar()).hide();
        setContentView(R.layout.activity_model_info);

        viewAR = findViewById(R.id.View_AR_Model_Button);
        viewAR.setOnClickListener(view -> {
            Toast.makeText(Model_Info.this, "View Model", Toast.LENGTH_SHORT).show();
            Intent intent  = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        });
        backButton = findViewById(R.id.Back_Button);
        favButton  = findViewById(R.id.Favourite_Button);
        moreButton = findViewById(R.id.More_Button);

        backButton.setOnClickListener(view -> {
//                Toast.makeText(Model_Info.this, "Back Button", Toast.LENGTH_SHORT).show();
            showAToast("Back Button");
        });

        favButton.setOnClickListener(view -> {
//                Toast.makeText(Model_Info.this, "Favourite Button", Toast.LENGTH_SHORT).show();
            showAToast("Favourite Button");
        });

        moreButton.setOnClickListener(view -> {
//                Toast.makeText(Model_Info.this, "More Button", Toast.LENGTH_SHORT).show();
            showAToast("More Button");
        });

    }
    public void showAToast (String message){

        if (mToast != null) {
            mToast.cancel();
        }
        mToast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
        mToast.show();
    }
}