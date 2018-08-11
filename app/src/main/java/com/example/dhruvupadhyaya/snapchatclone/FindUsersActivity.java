package com.example.dhruvupadhyaya.snapchatclone;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.EditText;

import com.example.dhruvupadhyaya.snapchatclone.RecyclerViewFollow.RCadapter;
import com.example.dhruvupadhyaya.snapchatclone.RecyclerViewFollow.UsersObject;

import java.util.ArrayList;

public class FindUsersActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.Adapter mAdapter;

    private Button mSearch;
    private EditText mInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_users);

        mRecyclerView = findViewById(R.id.recyclerView);
        mSearch = findViewById(R.id.searchUserButtonId);
        mInput = findViewById(R.id.searchUserEditText);

        mRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerView.setHasFixedSize(false);

        mLayoutManager = new LinearLayoutManager(getApplication());
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new RCadapter(getDataSet(),getApplication());
        mRecyclerView.setAdapter(mAdapter);

    }

    private ArrayList<UsersObject> results = new ArrayList<>();
    private ArrayList<UsersObject> getDataSet() {
        return results;
    }
}
