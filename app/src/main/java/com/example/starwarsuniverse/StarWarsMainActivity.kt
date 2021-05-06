package com.example.starwarsuniverse

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class StarWarsMainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_star_wars_main)

        val isFragmentContainerEmpty = savedInstanceState == null
        if (isFragmentContainerEmpty) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.starwarsmain, StarWarsFragment.newInstance())
                .commit()
        }
    }
}