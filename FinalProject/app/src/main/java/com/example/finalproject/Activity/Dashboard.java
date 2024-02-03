package com.example.finalproject.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.finalproject.R;

public class Dashboard extends AppCompatActivity {

    // mengatur pada bagian halaman menu utama / dashboard

    Button bKeluar;

    CardView cvMaster, cvTransaksi, cvLaporan, cvAboutme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        bKeluar = findViewById(R.id.bKeluar);
        cvMaster = findViewById(R.id.cvMaster);
        cvTransaksi = findViewById(R.id.cvTransaksi);
        cvLaporan = findViewById(R.id.cvLaporan);
        cvAboutme = findViewById(R.id.cvAboutme);

        // mengatur tombol keluar untuk mengarah kembali ke halaman Login
        bKeluar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Dashboard.this, Login.class));
            }
        });

        // mengarahakan ke halaman menu Master
        cvMaster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Dashboard.this, Master.class));
            }
        });

        // mengarahkan ke halaman menu Transaksi
        cvTransaksi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Dashboard.this, Transaksi.class));
            }
        });

        // mengarahkan ke halaman menu Laporan
        cvLaporan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Dashboard.this, Laporan.class));
            }
        });

        // mengarahkan ke halaman menu About Me
        cvAboutme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Dashboard.this, AboutMe.class));
            }
        });
    }
}