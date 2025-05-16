package com.example.creamlogin

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import me.relex.circleindicator.CircleIndicator3


class MainActivity : AppCompatActivity() {

    private lateinit var viewPager22: ViewPager2
    private lateinit var indicator: CircleIndicator3
    private lateinit var btnSignUp:Button
    private lateinit var createAccTV:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        createAccTV = findViewById(R.id.tvCreateAccount)
        btnSignUp = findViewById(R.id.btnSignIn)


        val pages = listOf(
            PageItem(R.drawable.image1, "Start a new social adventure."),
            PageItem(R.drawable.image2, "Connect with people nearby."),
            PageItem(R.drawable.image3, "Join events and communities.")
        )


        viewPager22 = findViewById(R.id.viewPager)
        indicator = findViewById(R.id.indicator)

        indicator.setViewPager(viewPager22)
        indicator.createIndicators(pages.size, 0) // Esto fuerza a dibujar los puntos
        viewPager22.registerOnPageChangeCallback(object:ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                indicator.animatePageSelected(position)
            }
        })



        viewPager22.adapter = ViewPagerAdapter(pages)



        // Botones
        findViewById<Button>(R.id.btnSignIn).setOnClickListener {
            startActivity(Intent(this,LoginActivity::class.java))
        }

        findViewById<TextView>(R.id.tvCreateAccount).setOnClickListener {
            startActivity(Intent(this,SignUpActivity::class.java))
        }


    }






}