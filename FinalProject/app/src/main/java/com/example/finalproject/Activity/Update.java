package com.example.finalproject.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.window.SplashScreen;

import com.example.finalproject.API.APIRequestData;
import com.example.finalproject.API.RetrofitServer;
import com.example.finalproject.Model.DataModelBarang;
import com.example.finalproject.Model.ModelBarang;
import com.example.finalproject.Model.ResponseModelBarang;
import com.example.finalproject.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Update extends AppCompatActivity {


    private String xKode, xNama, xSatuan, xHarga, xStok, xKey; //menampung data dari get
    private EditText etKode, etNama, etSatuan, etHarga, etStok;
    private TextView tvKey;
    private Button bUbah;
    private String yKode, yNama, ySatuan, yHarga, yStok, yTerjual, yKey; //variabel untuk mengambil data terbaru setelah di ubah
    private DatabaseReference dbr;
    private DataModelBarang modelBarang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        Intent terima = getIntent();
        xKey = terima.getStringExtra("xKey");
        xKode = terima.getStringExtra("xKode");
        xNama = terima.getStringExtra("xNama");
        xSatuan = terima.getStringExtra("xSatuan");
        xHarga = terima.getStringExtra("xHarga");
        xStok = terima.getStringExtra("xStok");

        tvKey = findViewById(R.id.tv_key);
        etNama = findViewById(R.id.et_nama);
        etSatuan = findViewById(R.id.et_satuan);
        etHarga = findViewById(R.id.et_harga);
        etStok = findViewById(R.id.et_stok);
        bUbah = findViewById(R.id.b_ubah);
        modelBarang = new DataModelBarang();
        dbr = FirebaseDatabase.getInstance().getReference();

        //di set datanya
        tvKey.setText(xKey);
        etNama.setText(xNama);
        etSatuan.setText(xSatuan);
        etHarga.setText(xHarga);
        etStok.setText(xStok);

        //jika btn ubah di klik
        bUbah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                yNama = etNama.getText().toString();
                ySatuan = etSatuan.getText().toString();
                yHarga = etHarga.getText().toString();
                yStok = etStok.getText().toString();

                // update firebase
                modelBarang.setNama(yNama);
                modelBarang.setSatuan(ySatuan);
                modelBarang.setHarga(yHarga);
                modelBarang.setStok(yStok);

                dbr.child("barang")
                        .child(tvKey.getText().toString())
                        .setValue(modelBarang)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(Update.this, "Update Sukses", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        });

                // update database mysql
                updateData();
            }
        });
    }
    // fungsi untuk mengupdate data
    private void updateData(){
        // menyambungkan database dengan menggunakan APIRequestData
        APIRequestData ardData = RetrofitServer.konekRetrofit().create(APIRequestData.class);
        Call<ResponseModelBarang> ubahData = ardData.ardUpdateData(xKode, yNama, ySatuan, yHarga, yStok, yTerjual);

        // pemberian respon ubah data
        ubahData.enqueue(new Callback<ResponseModelBarang>() {
            @Override
            public void onResponse(Call<ResponseModelBarang> call, Response<ResponseModelBarang> response) {
                String kode = response.body().getKode();
                String pesan = response.body().getPesan();
                Toast.makeText(Update.this, "Kode: "+kode+" | Pesan: "+pesan, Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<ResponseModelBarang> call, Throwable t) {
            }
        });
    }
}