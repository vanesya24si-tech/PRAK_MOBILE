package com.example.nesoulapps

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 1. Mengaktifkan fitur edge-to-edge (tampilan penuh hingga status bar)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // 2. Menangani padding otomatis agar tidak tertutup Status Bar/Navigasi
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // 3. Inisialisasi View & Logika
        val etNomor = findViewById<EditText>(R.id.etNomor)
        val btnKirim = findViewById<Button>(R.id.btnKirim)

        btnKirim.setOnClickListener {
            val nomor = etNomor.text.toString()
            if (nomor.isNotEmpty()) {
                Toast.makeText(this, "Mengirim ke: $nomor", Toast.LENGTH_SHORT).show()
            } else {
                etNomor.error = "Nomor tidak boleh kosong!"
            }
        }
    }
}