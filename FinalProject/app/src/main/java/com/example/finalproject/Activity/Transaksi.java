package com.example.finalproject.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.finalproject.API.APIRequestData;
import com.example.finalproject.API.RetrofitServer;
import com.example.finalproject.Adapter.AdapterBarang;
import com.example.finalproject.Model.DataModelBarang;
import com.example.finalproject.Model.ResponseModelBarang;
import com.example.finalproject.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Transaksi extends AppCompatActivity {

    // mengatur pada bagian halaman menu Transaksi
    private RecyclerView rvData;
    private RecyclerView.Adapter adData;
    private RecyclerView.LayoutManager lmData;
    private List<DataModelBarang> listData;
    private SwipeRefreshLayout srlData;
    private ProgressBar pbData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaksi);

        srlData = findViewById(R.id.swl_data);
        pbData = findViewById(R.id.pb_data);
        rvData = findViewById(R.id.rv_data);
        lmData = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvData.setLayoutManager(lmData);

        // untuk melakukan reset data jika setelah swipe data di app android
        srlData.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                srlData.setRefreshing(true);
                tampilData();
                srlData.setRefreshing(false);
            }
        });
    }

    // untuk bisa langsung mengupdate tampilan android jika data dihapus
    @Override
    protected void onResume() {
        super.onResume();
        tampilData();
    }

    // fungsi tampil data
    public void tampilData(){
        // menghubungkan class retrofit ke retrofitnya
        APIRequestData ardData = RetrofitServer.konekRetrofit().create(APIRequestData.class);
        Call<ResponseModelBarang> tampil = ardData.ardTampilData();

        tampil.enqueue(new Callback<ResponseModelBarang>() {
            // memberi respon tampilan
            @Override
            public void onResponse(Call<ResponseModelBarang> call, Response<ResponseModelBarang> response) {
                String kode = response.body().getKode();
                String pesan = response.body().getPesan();
                listData = response.body().getData();
                adData = new AdapterBarang(Transaksi.this, listData);
                rvData.setAdapter(adData);
                adData.notifyDataSetChanged();

                pbData.setVisibility(View.INVISIBLE);
            }

            // respon bila terjadi kegagalan menghubungkan ke server
            @Override
            public void onFailure(Call<ResponseModelBarang> call, Throwable t) {
                Toast.makeText(Transaksi.this, "Gagal Menghubungi Server: "+t.getMessage(), Toast.LENGTH_SHORT).show();

                pbData.setVisibility(View.INVISIBLE);
            }
        });
    }
}