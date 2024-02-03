package com.example.finalproject.API;

import android.util.Base64;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitServer {
    private static final String baseURL = "http://192.168.43.159:8080/penjualan/"; //menggunakan ipconfig pada ip address laptop
    private static Retrofit retro;

    public static Retrofit konekRetrofit(){
        if(retro==null){
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();

            //untuk membangun retrofit jika belum terdapat retrofit
            retro = new Retrofit.Builder()
                    .baseUrl(baseURL)
                    //untuk mengkonvert data gson ke android
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return retro;
    }
}
