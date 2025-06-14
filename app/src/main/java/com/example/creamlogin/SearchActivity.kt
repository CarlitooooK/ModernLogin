package com.example.creamlogin


import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import android.util.Log
import android.widget.EditText

import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.creamlogin.adapter.TituloAdapter
import com.example.creamlogin.retrofit.RetrofitInstance
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class SearchActivity:AppCompatActivity() {
    private lateinit var backIcon: ImageView
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: TituloAdapter

    @OptIn(DelicateCoroutinesApi::class)
    @SuppressLint("SoonBlockedPrivateApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_search)

        backIcon = findViewById(R.id.icBack)

        backIcon.setOnClickListener {
            finish()
        }

        searchViewPersonalize()

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        lifecycleScope.launch {
            try {
                val titulos = RetrofitInstance.api.getTitulos()
                adapter = TituloAdapter(titulos)
                recyclerView.adapter = adapter

            } catch (e: Exception) {
                Toast.makeText(this@SearchActivity, "Error al obtener los títulos", Toast.LENGTH_SHORT).show()
                e.printStackTrace()
            }
        }



    }

    @SuppressLint("SoonBlockedPrivateApi")
    private fun searchViewPersonalize() {
        val searchView = findViewById<SearchView>(R.id.searchView)

// Obtener el EditText interno para texto y hint
        val searchText = searchView.findViewById<EditText>(androidx.appcompat.R.id.search_src_text)
        searchText.setTextColor(Color.BLACK)
        searchText.setHintTextColor(Color.DKGRAY)

// Poner ícono lupa en negro
        val searchIcon = searchView.findViewById<ImageView>(androidx.appcompat.R.id.search_mag_icon)
        searchIcon.setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_IN)

// Poner botón "X" (limpiar) en negro
        val closeButton = searchView.findViewById<ImageView>(androidx.appcompat.R.id.search_close_btn)
        closeButton.setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_IN)

// Forzar que no esté iconificado (expandido)
        searchView.setIconifiedByDefault(false)

        try {
            val f = TextView::class.java.getDeclaredField("mCursorDrawableRes")
            f.isAccessible = true
            f.set(searchText, R.drawable.cursor_black)  // tu drawable negro
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}