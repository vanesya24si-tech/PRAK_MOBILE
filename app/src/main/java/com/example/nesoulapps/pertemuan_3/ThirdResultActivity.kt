package com.example.nesoulapps.pertemuan_3

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.nesoulapps.MainActivity
import com.example.nesoulapps.R
import com.example.nesoulapps.databinding.ActivityThirdResultBinding // Import binding

class ThirdResultActivity : AppCompatActivity() {

    // 1. Deklarasikan binding
    private lateinit var binding: ActivityThirdResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // 2. Inisialisasi binding
        binding = ActivityThirdResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 3. Pastikan ID 'main' ada di XML agar baris ini tidak error/crash
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // 4. Tambahkan logika klik tombol kembali
        binding.btnKembali.setOnClickListener {
            // Berpindah ke MainActivity
            val intent = Intent(this, MainActivity::class.java)
            // Menghapus tumpukan activity agar balik ke awal dengan bersih
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            finish() // Menutup halaman ini
        }
    }
}