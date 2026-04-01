package com.example.nesoulapps.pertemuan_3

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.nesoulapps.databinding.ActivityThirdBinding

class ThirdActivity : AppCompatActivity() {

    private lateinit var binding: ActivityThirdBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 1. Aktifkan tampilan layar penuh
        enableEdgeToEdge()

        // 2. Inisialisasi View Binding
        binding = ActivityThirdBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 3. Padding System Bars
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // 4. Logika Klik Tombol
        binding.btnKirim.setOnClickListener {
            val nomor = binding.etNomor.text.toString()

            if (nomor.isNotEmpty()) {
                // JIKA ISI: PINDAH HALAMAN
                val intent = Intent(this, ThirdResultActivity::class.java)
                startActivity(intent)
            } else {
                // JIKA KOSONG: KASIH ERROR (JANGAN ADA INTENT DI SINI)
                binding.etNomor.error = "Mohon isi nomor tujuan!"
                Toast.makeText(this, "Nomor tidak boleh kosong", Toast.LENGTH_SHORT).show()
            }
        }
    }
}