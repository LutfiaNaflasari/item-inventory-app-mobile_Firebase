package com.example.finalproject.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalproject.API.APIRequestData;
import com.example.finalproject.API.RetrofitServer;
import com.example.finalproject.Activity.Detail;
import com.example.finalproject.Activity.Transaksi;
import com.example.finalproject.Activity.Update;
import com.example.finalproject.Model.DataModelBarang;
import com.example.finalproject.Model.ResponseModelBarang;
import com.example.finalproject.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdapterBarang extends RecyclerView.Adapter<AdapterBarang.HolderData> {
    private Context context; //context untuk mewakilkan activity di post activity
    private List<DataModelBarang> listModel;
    private List<DataModelBarang> listBarang;
    private String idBarang, key;

    //buat konstruktor
    public AdapterBarang(Context context, List<DataModelBarang> listModel) {
        this.context = context;
        this.listModel = listModel;
    }

    @NonNull
    @Override
    //melakukan inflate data dari card_item.xml ke recyclerviewnya
    public HolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item, parent, false);
        HolderData holder = new HolderData(layout);
        return holder;
    }

    @Override
    //meletakkan setiap textview yang sudah di findview pada viewholder
    public void onBindViewHolder(@NonNull HolderData holder, @SuppressLint("RecyclerView") int position) {
        final DataModelBarang dm = listModel.get(position); // sebagai set data

        holder.tvKey.setText(dm.getKey());
        holder.tvKode.setText(dm.getKode());
        holder.tvNama.setText(dm.getNama());
        holder.tvSatuan.setText(dm.getSatuan());
        holder.tvHarga.setText(dm.getHarga());
        holder.tvStok.setText(dm.getStok());
        holder.tvTerjual.setText(dm.getTerjual());
        holder.listLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String varKey = listModel.get(position).getKey();
                String varKodeBarang = listModel.get(position).getKode();
                String varNamaBarang = listModel.get(position).getNama();
                String varSatuanBarang = listModel.get(position).getSatuan();
                String varHargaBarang = listModel.get(position).getHarga();
                String varStokBarang = listModel.get(position).getStok();
                String varTerjual = listModel.get(position).getTerjual();

                Intent kirim = new Intent(context, Detail.class);
                kirim.putExtra("xKey", varKey);
                kirim.putExtra("xKode", varKodeBarang);
                kirim.putExtra("xNama", varNamaBarang);
                kirim.putExtra("xSatuan", varSatuanBarang);
                kirim.putExtra("xHarga", varHargaBarang);
                kirim.putExtra("xStok", varStokBarang);
                kirim.putExtra("xTerjual", varTerjual);
                context.startActivity(kirim);
            }
        });
    }

    @Override
    //mengetahui data yang diambil seberapa banyak
    public int getItemCount() {
        return listModel.size();
    }

    //untuk mengatur data yang tersedia pada cardview
    public class HolderData extends RecyclerView.ViewHolder {
        TextView tvKode, tvNama, tvSatuan, tvHarga, tvStok, tvTerjual, tvKey;
        ImageView ivGambar;
        LinearLayout listLayout;

        //konstruktor
        public HolderData(@NonNull View v) {
            super(v);
            //v. karena data berada pada variable v yg dimana berada di ViewHolder maka ijin terlebih dahulu
            //ivGambar = v.findViewById(R.id.iv_barang);
            //tvId = v.findViewById(R.id.tv_id);
            listLayout = v.findViewById(R.id.list_item);
            tvKode = v.findViewById(R.id.tv_kode);
            tvNama = v.findViewById(R.id.tv_nama);
            tvSatuan = v.findViewById(R.id.tv_satuan);
            tvHarga = v.findViewById(R.id.tv_harga);
            tvStok = v.findViewById(R.id.tv_stok);
            tvTerjual = v.findViewById(R.id.tv_terjual);
            tvKey = v.findViewById(R.id.tv_key);
        }
    }
}

