package com.example.nesoulapps.pertemuan_6 // Nama package baru

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.content.edit
import com.example.nesoulapps.R // Pastikan R mengarah ke package yang benar
import com.example.nesoulapps.databinding.ActivityAuthBinding
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

            // Perbaikan logika: Pastikan input tidak kosong
            if (inputNama.isNotEmpty() && inputNama == inputPassword) {
                sharedPref.edit {
                    putBoolean("isLogin", true)
                    putString("username", inputNama)
                }
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish() // Tutup AuthActivity agar tidak bisa kembali dengan tombol back
            } else {
                MaterialAlertDialogBuilder(this)
                    .setTitle("Login Gagal")
                    .setMessage("Username atau password salah atau kosong!")
                    .setPositiveButton("OK", null)
                    .show()
            }
        }
    }
}