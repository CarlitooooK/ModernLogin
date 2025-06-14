package com.example.creamlogin

import androidx.fragment.app.Fragment
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.creamlogin.fragments.FormFragment
import com.example.creamlogin.fragments.SearchFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.core.view.get

class MainActivity : AppCompatActivity() {

    private lateinit var bottomAppBar: BottomNavigationView
    private lateinit var viewPager2: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        viewPager2 = findViewById(R.id.viewPager2)
        bottomAppBar = findViewById(R.id.bottomNavigationView)

        viewPager2.adapter = ScreenSlidePagerAdapter(this)

        // Al seleccionar una opción en el BottomNav, cambia el ViewPager
        bottomAppBar.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.search -> viewPager2.currentItem = 0
                R.id.add -> viewPager2.currentItem = 1
            }
            true
        }

        // Al deslizar, actualiza el ítem seleccionado en el BottomNav
        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                bottomAppBar.menu[position].isChecked = true
            }
        })
    }

    private inner class ScreenSlidePagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
        override fun getItemCount(): Int = 2
        override fun createFragment(position: Int): Fragment = when (position) {
            0 -> SearchFragment()
            1 -> FormFragment()
            else -> throw IllegalStateException("Posición inválida: $position")
        }
    }


    }
