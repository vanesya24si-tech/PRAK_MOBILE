package com.example.nesoulapps.pertemuan_9

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.nesoulapps.databinding.ActivityNinthBinding
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup

class NinthActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNinthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityNinthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Setup Toolbar back button
        binding.toolbar.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        // Logic untuk mendeteksi Chip yang dipilih
        binding.chipGroupFilter.setOnCheckedStateChangeListener { group: ChipGroup, checkedIds: List<Int> ->
            val selectedChipId = checkedIds.firstOrNull() // Ambil ID chip yang dipilih
            if (selectedChipId != null) {
                val chip = group.findViewById<Chip>(selectedChipId)
                Toast.makeText(this, "Filter: ${chip.text}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}