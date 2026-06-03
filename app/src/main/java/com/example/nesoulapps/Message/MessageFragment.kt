package com.example.nesoulapps.Message

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.nesoulapps.Message.tutorial.TutorialMessageActivity
import com.example.nesoulapps.R
import com.example.nesoulapps.databinding.FragmentMessageBinding

class MessageFragment : Fragment() {
    private var _binding: FragmentMessageBinding? = null
    private val binding get() = _binding!!

    private val messageList = listOf(
        MessageModel("Alya", "Halo! Apa kabar?", "https://avatar.iran.liara.run/public/1"),
        MessageModel("Budi", "Sudah makan?", "https://avatar.iran.liara.run/public/2"),
        MessageModel("Citra", "Jangan lupa tugasnya ya!", "https://avatar.iran.liara.run/public/3"),
        MessageModel("Dika", "Besok kita rapat jam 9", "https://avatar.iran.liara.run/public/4"),
        MessageModel("Eka", "Nice job kemarin!", "https://avatar.iran.liara.run/public/5"),
        MessageModel("Fajar", "Lagi ngapain?", "https://avatar.iran.liara.run/public/6"),
        MessageModel("Gita", "Boleh minta tolong?", "https://avatar.iran.liara.run/public/7"),
        MessageModel("Hana", "Lihat email ya", "https://avatar.iran.liara.run/public/8"),
        MessageModel("Irfan", "Oke noted", "https://avatar.iran.liara.run/public/9"),
        MessageModel("Joko", "Sampai jumpa besok", "https://avatar.iran.liara.run/public/10")
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMessageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        // Debug: Pastikan fragment ini dimuat
        Log.d("MessageFragment", "onViewCreated called")

        // Konfigurasi Toolbar secara manual & paksa
        binding.toolbar.title = "Message"
        
        // Pastikan menu bersih sebelum inflate
        binding.toolbar.menu.clear()
        binding.toolbar.inflateMenu(R.menu.message_toolbar_menu)

        // Penanganan klik menu item
        binding.toolbar.setOnMenuItemClickListener { item ->
            Log.d("MessageFragment", "Menu item clicked: ${item.itemId}")
            
            if (item.itemId == R.id.action_tutorial) {
                // Tampilkan Toast: Jika ini muncul, berarti kode klik jalan!
                Toast.makeText(requireContext(), "Membuka Tutorial...", Toast.LENGTH_SHORT).show()
                
                try {
                    val intent = Intent(requireContext(), TutorialMessageActivity::class.java)
                    startActivity(intent)
                } catch (e: Exception) {
                    Log.e("MessageFragment", "Gagal membuka activity: ${e.message}")
                    Toast.makeText(requireContext(), "Gagal membuka tutorial", Toast.LENGTH_LONG).show()
                }
                true
            } else {
                false
            }
        }

        val adapter = MessageAdapter(requireContext(), messageList)
        binding.listMessageItems.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
