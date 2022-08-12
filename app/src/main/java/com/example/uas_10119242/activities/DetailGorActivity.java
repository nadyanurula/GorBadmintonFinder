package com.example.uas_10119242.activities;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.uas_10119242.R;
import com.example.uas_10119242.model.ModelGor;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.picasso.Picasso;


public class DetailGorActivity extends AppCompatActivity implements OnMapReadyCallback {

    TextView tvNama, tvDesc, tvAlamat, tvJam;
    ImageView imgWisata;
    Toolbar tbWisata;
    ModelGor ModelGor = null;

    public MapView mapView;
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_alam);

        tbWisata = findViewById(R.id.toolbar_wisata);
        tbWisata.setTitle("Detail GOR");
        setSupportActionBar(tbWisata);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final Object object = getIntent().getSerializableExtra("detailData");

        if (object instanceof ModelGor) {
            ModelGor = (ModelGor) object;
        }

        imgWisata = findViewById(R.id.imgWisata);
        tvNama = findViewById(R.id.tvNama);
        tvDesc = findViewById(R.id.tvDeskripsi);
        tvAlamat = findViewById(R.id.tvAlamat);
        mapView = findViewById(R.id.mapView);

        if (ModelGor != null) {
            Picasso.get()
                    .load(ModelGor.getGambar())
                    .into(imgWisata);
            tvNama.setText(ModelGor.getNama());
            tvAlamat.setText(ModelGor.getAlamat());

            mapView.onCreate(null);
            mapView.getMapAsync(this);

        } else {
            tvNama.setText("Gaada datanyaa");
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;

        mMap.getUiSettings().setMapToolbarEnabled(false);
        LatLng newLocation = new LatLng(ModelGor.getLatitude(), ModelGor.getLongitude());
        mMap.addMarker(new MarkerOptions().position(newLocation).title(ModelGor.getNama()));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(newLocation));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(newLocation, 15));

        mMap.getUiSettings().setZoomGesturesEnabled(true);
        mMap.getUiSettings().setAllGesturesEnabled(true);
        mMap.setTrafficEnabled(true);
    }
}