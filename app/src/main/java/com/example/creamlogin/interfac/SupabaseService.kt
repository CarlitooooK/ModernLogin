package com.example.creamlogin.interfac

import com.example.creamlogin.models.Corto
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface SupabaseService {
    @Headers(
        "apikey: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InV1d2V3eXBub2Z1a3lubG5kbHN1Iiwicm9sZSI6ImFub24iLCJpYXQiOjE3NDk4NDE5ODMsImV4cCI6MjA2NTQxNzk4M30.4phV5-9SNaPaq3XgNLueAQSwxl9Oltd3F5IIyourrOw",
        "Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InV1d2V3eXBub2Z1a3lubG5kbHN1Iiwicm9sZSI6ImFub24iLCJpYXQiOjE3NDk4NDE5ODMsImV4cCI6MjA2NTQxNzk4M30.4phV5-9SNaPaq3XgNLueAQSwxl9Oltd3F5IIyourrOw",
        "Content-Type: application/json"
    )
    @GET("rest/v1/acervo")
    suspend fun getCortos(
        @Query("select") select: String = "titulo_original,sinopsis,genero,basado_en"
    ): List<Corto>

}
