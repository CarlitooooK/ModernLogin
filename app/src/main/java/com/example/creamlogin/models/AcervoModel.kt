package com.example.creamlogin.models

import com.google.gson.annotations.SerializedName

data class Corto(
    @SerializedName("titulo_original") val tituloOriginal: String?,
    @SerializedName("descripcion") val descripcion: String?, // si no hay campo 'descripcion', puedes usar sinopsis
    @SerializedName("genero") val genero: String?,
    @SerializedName("sinopsis") val sinopsis: String?
)
