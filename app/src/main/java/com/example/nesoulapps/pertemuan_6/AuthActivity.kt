package com.example.nesoulapps.pertemuan_6

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.content.edit
import com.example.nesoulapps.R
import com.example.nesoulapps.databinding.ActivityAuthBinding
import com.example.nesoulapps.pertemuan7.MainActivityP7
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class AuthActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAuthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val sharedPref = getSharedPreferences("user_pref", MODE_PRIVATE)

        binding.btnlogin.setOnClickListener {
            val inputNama = binding.inputNama.text.toString()
            val inputPassword = binding.inputpassword.text.toString()

            if (inputNama.isNotEmpty() && inputNama == inputPassword) {
                sharedPref.edit {
                    putBoolean("isLogin", true)
                    putString("username", inputNama)
                }
                // Navigasi ke MainActivityP7 di folder pertemuan7
                val intent = Intent(this, MainActivityP7::class.java)
                startActivity(intent)
                finish()
            } else {
                MaterialAlertDialogBuilder(this)
                    .setTitle("Login Gagal")
                    .setMessage("Username atau password salah!")
                    .setPositiveButton("OK", null)
                    .show()
            }
        }
    }
}