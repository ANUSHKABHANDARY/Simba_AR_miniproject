package com.simba.argenesis;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class All_Categories extends AppCompatActivity {

    Toast mToast = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_categories);

        CardView catSolarSystem = findViewById(R.id.catSolarSystem);
        catSolarSystem.setOnClickListener(view -> {
//            Intent intent = new Intent(getApplicationContext(), Model_Info.class);
//            startActivity(intent);
            finish();
        });
    }

    public void showAToast(String message) {

        if (mToast != null) {
            mToast.cancel();
        }
        mToast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
        mToast.show();
    }
}