package com.example.nesoulapps.More

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SimpleAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.nesoulapps.databinding.FragmentMoreBinding

class MoreFragment : Fragment() {
    private var _binding: FragmentMoreBinding? = null
    private val binding get() = _binding!!

    private val dataListWithDesc = listOf(
        mapOf("title" to "Kotlin", "desc" to "Bahasa untuk Android modern"),
        mapOf("title" to "Java", "desc" to "Bahasa OOP yang populer"),
        mapOf("title" to "Python", "desc" to "Bahasa yang mudah dipahami"),
        mapOf("title" to "JavaScript", "desc" to "Bahasa utama web development"),
        mapOf("title" to "Dart", "desc" to "Bahasa untuk framework Flutter"),
        mapOf("title" to "Swift", "desc" to "Bahasa untuk aplikasi iOS"),
        mapOf("title" to "Go", "desc" to "Bahasa backend buatan Google"),
        mapOf("title" to "PHP", "desc" to "Bahasa server-side klasik")
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMoreBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Menggunakan SimpleAdapter untuk layout simple_list_item_2 (Text + Desc)
        val adapter = SimpleAdapter(
            requireContext(),
            dataListWithDesc,
            R.layout.simple_list_item_2,
            arrayOf("title", "desc"),
            intArrayOf(R.id.text1, R.id.text2)
        )

        // Hubungkan listViewItems dengan adapter
        binding.listViewItems.adapter = adapter

        // Tambahkan aksi saat item di-list diklik
        binding.listViewItems.setOnItemClickListener { _, _, position, _ ->
            val selectedItem = dataListWithDesc[position]
            val title = selectedItem["title"]
            val desc = selectedItem["desc"]
            Toast.makeText(requireContext(), "Kamu memilih: $title ($desc)", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}