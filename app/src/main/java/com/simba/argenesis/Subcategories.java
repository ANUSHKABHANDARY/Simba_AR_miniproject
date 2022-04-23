package com.simba.argenesis;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class Subcategories extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<Models> modelsArrayList;
    recyclerViewAdapter recyclerViewAdapter;
    FirebaseFirestore db;
    private ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subcategories);


//        Subcat_models[] subcatmodels = new Subcat_models[]{
//                new Subcat_models("Earth",R.drawable.earth),
//                new Subcat_models("Mars",R.drawable.mars),
//                new Subcat_models("Mercury",R.drawable.mercury),
//                new Subcat_models("Earth",R.drawable.earth),
//                new Subcat_models("Mars",R.drawable.mars),
//                new Subcat_models("Mercury",R.drawable.mercury),
//                new Subcat_models("Earth",R.drawable.earth),
//                new Subcat_models("Mars",R.drawable.mars),
//                new Subcat_models("Mercury",R.drawable.mercury),
//                new Subcat_models("Earth",R.drawable.earth),
//                new Subcat_models("Mars",R.drawable.mars),
//                new Subcat_models("Mercury",R.drawable.mercury),
//        };

//        Subcat_adapter subcat_adapter = new Subcat_adapter(subcatmodels,Subcategories.this);

        back = findViewById(R.id.back);
        back.setOnClickListener(v -> {
            Intent intent = new Intent(Subcategories.this, All_Categories.class);
            startActivity(intent);
        });

        recyclerView = findViewById(R.id.subCategoriesRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);

        recyclerView.setLayoutManager(gridLayoutManager);

        db = FirebaseFirestore.getInstance();
        modelsArrayList = new ArrayList<Models>();
        recyclerViewAdapter = new recyclerViewAdapter(Subcategories.this, modelsArrayList, 2);

        recyclerView.setAdapter(recyclerViewAdapter);


        EventChangeListener();
    }

    private void EventChangeListener() {

        db.collection("Model_Information").document("Models").collection("Solar System")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                        if (error != null) {
                            Log.e("Firestore Error", error.getMessage());
                            return;
                        }

                        for (DocumentChange documentChange : value.getDocumentChanges()) {

                            if (documentChange.getType() == DocumentChange.Type.ADDED) {

                                modelsArrayList.add(documentChange.getDocument().toObject(Models.class));


                                recyclerViewAdapter.notifyDataSetChanged();
                            }
                        }
                    }
                });
    }
}