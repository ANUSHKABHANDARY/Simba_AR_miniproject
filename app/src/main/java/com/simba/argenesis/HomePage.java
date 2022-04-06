package com.simba.argenesis;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class HomePage extends AppCompatActivity {

//    private ListView mListView;
//    private final String[] names = {"Earth", "Moon"};
//    private final int[] images = {R.drawable.earth, R.drawable.moon};

    RecyclerView recyclerView;
    ArrayList<Models> modelsArrayList;
    recyclerViewAdapter recyclerViewAdapter;
    FirebaseFirestore db;
    Models models;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        recyclerView = findViewById(R.id.featuredModelsRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        LinearLayoutManager HorizontalLayout = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(HorizontalLayout);

        db = FirebaseFirestore.getInstance();
        modelsArrayList = new ArrayList<Models>();
        recyclerViewAdapter = new recyclerViewAdapter(HomePage.this, modelsArrayList);

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