package com.example.finalproject.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.finalproject.API.APIRequestData;
import com.example.finalproject.API.RetrofitServer;
import com.example.finalproject.Adapter.AdapterBarang;
import com.example.finalproject.Adapter.AdapterLaporan;
import com.example.finalproject.Model.DataModelBarang;
import com.example.finalproject.Model.ResponseModelBarang;
import com.example.finalproject.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Laporan extends AppCompatActivity {

    private RecyclerView rvData;
    private RecyclerView.Adapter adData;
    private RecyclerView.LayoutManager lmData;
    private List<DataModelBarang> listData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laporan);

        rvData = findViewById(R.id.rv_data);
        lmData = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvData.setLayoutManager(lmData);

        tampilData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        tampilData();
    }

    public void tampilData(){
        APIRequestData ardData = RetrofitServer.konekRetrofit().create(APIRequestData.class);
        Call<ResponseModelBarang> tampil = ardData.ardTampilData();

        tampil.enqueue(new Callback<ResponseModelBarang>() {
            @Override
            public void onResponse(Call<ResponseModelBarang> call, Response<ResponseModelBarang> response) {
                String kode = response.body().getKode();
                String pesan = response.body().getPesan();
                listData = response.body().getData();
                adData = new AdapterLaporan(Laporan.this, listData);
                rvData.setAdapter(adData);
                adData.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ResponseModelBarang> call, Throwable t) {
                Toast.makeText(Laporan.this, "Gagal Menghubungi Server: "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}