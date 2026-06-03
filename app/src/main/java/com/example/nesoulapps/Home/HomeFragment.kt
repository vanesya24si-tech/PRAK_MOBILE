package com.example.nesoulapps.Home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nesoulapps.Home.pertemuan_10.TenthActivity
import com.example.nesoulapps.Home.photo.PhotoAdapter
import com.example.nesoulapps.MainActivity
import com.example.nesoulapps.data.api.PhotoApiClient
import com.example.nesoulapps.data.api.RetrofitClient
import com.example.nesoulapps.data.model.CatFactResponse
import com.example.nesoulapps.databinding.FragmentHomeBinding
import com.example.nesoulapps.pertemuan_2.SecondActivity
import com.example.nesoulapps.pertemuan_3.ThirdActivity
import com.example.nesoulapps.pertemuan_4.FourthActivity
import com.example.nesoulapps.pertemuan_5.FifthActivity
import com.example.nesoulapps.pertemuan_9.NinthActivity
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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
        val username = sharedPref.getString("username", "Zakky")
        
        binding.tvWelcome.text = "Welcome, $username!"

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
        binding.btnP10.setOnClickListener {
            startActivity(Intent(requireContext(), TenthActivity::class.java))
        }

        binding.btnRefresh.setOnClickListener {
            loadFact()
        }

        loadFact()
        loadPhoto()
    }

    private fun loadFact() {
        binding.tvFact.text = "Loading fact..."
        RetrofitClient.instance.getCatFact().enqueue(object : Callback<CatFactResponse> {
            override fun onResponse(call: Call<CatFactResponse>, response: Response<CatFactResponse>) {
                if (response.isSuccessful) {
                    binding.tvFact.text = response.body()?.fact
                } else {
                    binding.tvFact.text = "Failed to load fact"
                }
            }

            override fun onFailure(call: Call<CatFactResponse>, t: Throwable) {
                binding.tvFact.text = "Error: ${t.message}"
            }
        })
    }

    private fun loadPhoto() {
        lifecycleScope.launch {
            try {
                val photos = PhotoApiClient.apiService.getPhotos()
                val adapter = PhotoAdapter(photos)
                binding.rvGallery.adapter = adapter
                binding.rvGallery.layoutManager = LinearLayoutManager(requireContext())
            } catch (e: Exception) {
                Toast.makeText(requireContext(), "Gagal memuat gambar: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
