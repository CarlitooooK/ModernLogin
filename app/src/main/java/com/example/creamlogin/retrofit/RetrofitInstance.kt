package com.example.creamlogin.retrofit

import com.example.creamlogin.interfac.SupabaseService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    val api: SupabaseService by lazy {
        Retrofit.Builder()
            .baseUrl("https://uuwewypnofukynlndlsu.supabase.co/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(SupabaseService::class.java)
    }
}
