package com.example.nesoulapps.pertemuan7

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.nesoulapps.databinding.FragmentHomeBinding
import com.example.nesoulapps.pertemuan_2.SecondActivity
import com.example.nesoulapps.pertemuan_3.ThirdActivity
import com.example.nesoulapps.pertemuan_4.FourthActivity
import com.example.nesoulapps.pertemuan_5.FifthActivity
import com.example.nesoulapps.pertemuan_6.MainActivity
import com.example.nesoulapps.pertemuan_9.NinthActivity

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharedPref = requireActivity().getSharedPreferences("user_pref", Context.MODE_PRIVATE)
        val username = sharedPref.getString("username", "Vanesya")
        
        // Memperbaiki error: tvHomeName sudah diganti menjadi tvWelcome di layout baru
        binding.tvWelcome.text = "Welcome, $username!"

        // Listener untuk tombol-tombol Pertemuan
        binding.btnP2.setOnClickListener {
            startActivity(Intent(requireContext(), SecondActivity::class.java))
        }
        
        binding.btnP3.setOnClickListener {
            startActivity(Intent(requireContext(), ThirdActivity::class.java))
        }
        
        binding.btnP4.setOnClickListener {
            startActivity(Intent(requireContext(), FourthActivity::class.java))
        }
        
        binding.btnP5.setOnClickListener {
            startActivity(Intent(requireContext(), FifthActivity::class.java))
        }
        
        binding.btnP6.setOnClickListener {
            startActivity(Intent(requireContext(), MainActivity::class.java))
        }

        binding.btnP9.setOnClickListener {
            startActivity(Intent(requireContext(), NinthActivity::class.java))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}