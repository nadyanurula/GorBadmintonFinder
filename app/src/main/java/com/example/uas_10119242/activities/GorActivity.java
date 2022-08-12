package com.example.uas_10119242.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uas_10119242.R;
import com.example.uas_10119242.Tools;
import com.example.uas_10119242.adapter.AlamAdapter;
import com.example.uas_10119242.layout.LayoutMarginDecoration;
import com.example.uas_10119242.model.ModelGor;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class GorActivity extends AppCompatActivity implements AlamAdapter.onSelectData {

    RecyclerView rvWisata;
    AlamAdapter AlamAdapter;
    LayoutMarginDecoration gridMargin;
    List<ModelGor> lsAlam = new ArrayList<>();
    DatabaseReference reference;
    Toolbar tbWisata;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alam);

        tbWisata = findViewById(R.id.toolbar_wisata);
        tbWisata.setTitle("Daftar GOR Badminton");
        setSupportActionBar(tbWisata);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Mohon Tunggu");
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Sedang menampilkan data...");

        rvWisata = findViewById(R.id.wisataList);
        GridLayoutManager mLayoutManager = new GridLayoutManager(this,
                2, RecyclerView.VERTICAL, false);
        rvWisata.setLayoutManager(mLayoutManager);
        gridMargin = new LayoutMarginDecoration(2, Tools.dp2px(this, 4));
        rvWisata.addItemDecoration(gridMargin);
        rvWisata.setHasFixedSize(true);

        reference = FirebaseDatabase.getInstance("https://nadya-137ea-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference().child("Badminton");

        AlamAdapter = new AlamAdapter(this, lsAlam, this);
        rvWisata.setAdapter(AlamAdapter);

        progressDialog.show();

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    ModelGor ModelGor = dataSnapshot.getValue(ModelGor.class);
                    lsAlam.add(ModelGor);
                }
                AlamAdapter.notifyDataSetChanged();
                progressDialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    @Override
    public void onSelected(ModelGor ModelGor) {
         Intent intent = new Intent(GorActivity.this, DetailGorActivity.class);
            intent.putExtra("detailData", ModelGor);
            startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}