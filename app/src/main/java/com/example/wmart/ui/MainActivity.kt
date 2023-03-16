package com.example.wmart.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import com.example.wmart.R
import com.google.android.material.appbar.CollapsingToolbarLayout

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setToolbar()

        var fragment : CountryListFragment
        if(savedInstanceState == null) {
            fragment = CountryListFragment()
        } else {
            fragment = supportFragmentManager.findFragmentByTag(CountryListFragment.TAG) as CountryListFragment
        }

        supportFragmentManager.beginTransaction().replace(R.id.container, fragment, CountryListFragment.TAG).commit()
    }

    private fun setToolbar() {
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val collapsibleToolbar = findViewById<CollapsingToolbarLayout>(R.id.collapse)
        collapsibleToolbar.title = "Countries"
    }
}