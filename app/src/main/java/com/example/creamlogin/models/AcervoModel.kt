package com.example.creamlogin.models

import com.google.gson.annotations.SerializedName

data class Corto(
    @SerializedName("titulo_original") val tituloOriginal: String?,
    @SerializedName("clasificacion") val clasificacion: String?, // si no hay campo 'descripcion', puedes usar sinopsis
    @SerializedName("genero") val genero: String?,
    @SerializedName("sinopsis") val sinopsis: String?,
    @SerializedName("basado_en ") val basadoEn: String?,
    @SerializedName("duracion") val duracion: String?
)
