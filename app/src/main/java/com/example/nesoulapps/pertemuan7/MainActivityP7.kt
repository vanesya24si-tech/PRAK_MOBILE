package com.example.nesoulapps.pertemuan7

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.nesoulapps.Message.MessageFragment
import com.example.nesoulapps.More.MoreFragment
import com.example.nesoulapps.R
import com.example.nesoulapps.databinding.ActivityMainP7Binding

class MainActivityP7 : AppCompatActivity() {

    private lateinit var binding: ActivityMainP7Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainP7Binding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set default fragment
        loadFragment(HomeFragment())

        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    loadFragment(HomeFragment())
                    true
                }
                R.id.nav_dashboard -> {
                    loadFragment(DashboardFragment())
                    true
                }
                R.id.nav_message -> {
                    loadFragment(MessageFragment())
                    true
                }
                R.id.nav_more -> {
                    loadFragment(MoreFragment())
                    true
                }
                R.id.nav_profile -> {
                    loadFragment(ProfileFragment())
                    true
                }
                else -> false
            }
        }
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }
}