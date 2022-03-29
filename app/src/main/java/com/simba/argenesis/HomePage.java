package com.simba.argenesis;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class HomePage extends AppCompatActivity {

    private ListView mListView;
    private String [] names = {"Earth", "Moon"};
    private int [] images = {R.drawable.earth, R.drawable.moon};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
//        RecyclerView mListView = findViewById(R.id.listview);
//
//        MyAdapter adapter = new MyAdapter();
//        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
//        mLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
//        mListView.setLayoutManager(mLayoutManager);
//        mListView.setAdapter(adapter);
        RecyclerView recyclerView = findViewById(R.id.listview);
        MyAdapter mAdapter = new MyAdapter(names);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(mAdapter);

    }
    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

        private String[] localDataSet;

        public MyAdapter(String[] names) {
            localDataSet = names;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.card_home, parent, false);
            return new MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            holder.mTextView.setText(names[position]);
            holder.mImageView.setImageResource(images[position]);
        }

        @Override
        public int getItemCount() {
            return images.length;
        }

        class MyViewHolder extends RecyclerView.ViewHolder {
            private TextView mTextView;
            private ImageView mImageView;

            MyViewHolder(View view) {
                super(view);
                mImageView = view.findViewById(R.id.photo);
                mTextView = (TextView) view.findViewById(R.id.abcd);
            }
        }
    }
}