package com.example.creamlogin.fragments

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.SearchView
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.creamlogin.R
import com.example.creamlogin.adapter.TituloAdapter
import com.example.creamlogin.retrofit.RetrofitInstance
import kotlinx.coroutines.launch

class SearchFragment : Fragment() {

    private lateinit var backIcon: ImageView
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: TituloAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.R)
    @SuppressLint("SoonBlockedPrivateApi", "WrongConstant")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val window = requireActivity().window
        WindowCompat.setDecorFitsSystemWindows(window, false)

        val controller = WindowInsetsControllerCompat(window, view)
        controller.hide(android.view.WindowInsets.Type.statusBars())
        controller.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_DEFAULT

        backIcon = view.findViewById(R.id.icBack)
        recyclerView = view.findViewById(R.id.recyclerView)

        backIcon.setOnClickListener {
            // Si quieres ocultar el fragmento actual puedes usar una navegación aquí
            parentFragmentManager.popBackStack() // o cualquier acción de navegación
        }

        setupSearchView(view)

        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        viewLifecycleOwner.lifecycleScope.launch {
            try {
                val titulos = RetrofitInstance.api.getTitulos()
                adapter = TituloAdapter(titulos)
                recyclerView.adapter = adapter
            } catch (e: Exception) {
                Toast.makeText(requireContext(), "Error al obtener los títulos", Toast.LENGTH_SHORT).show()
                e.printStackTrace()
            }
        }
    }

    @SuppressLint("SoonBlockedPrivateApi")
    private fun setupSearchView(view: View) {
        val searchView = view.findViewById<SearchView>(R.id.searchView)

        val searchText = searchView.findViewById<EditText>(androidx.appcompat.R.id.search_src_text)
        searchText.setTextColor(Color.BLACK)
        searchText.setHintTextColor(Color.DKGRAY)

        val searchIcon = searchView.findViewById<ImageView>(androidx.appcompat.R.id.search_mag_icon)
        searchIcon.setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_IN)

        val closeButton = searchView.findViewById<ImageView>(androidx.appcompat.R.id.search_close_btn)
        closeButton.setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_IN)

        searchView.setIconifiedByDefault(false)

        try {
            val f = TextView::class.java.getDeclaredField("mCursorDrawableRes")
            f.isAccessible = true
            f.set(searchText, R.drawable.cursor_black)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
