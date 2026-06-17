package com.example.nesoulapps.Home.pertemuan_13

import android.Manifest
import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.nesoulapps.databinding.FragmentTabCaptureBinding

class TabCaptureFragment : Fragment() {

    private var _binding: FragmentTabCaptureBinding? = null
    private val binding get() = _binding!!

    private var currentPhotoUri: Uri? = null

    // Launcher untuk menangani hasil pengambilan foto dari kamera
    private val cameraLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            currentPhotoUri?.let { uri ->
                binding.ivCapturedImage.setImageURI(uri)
                Toast.makeText(requireContext(), "Foto berhasil diambil", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Launcher untuk meminta izin kamera
    private val permissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
        if (isGranted) {
            openCamera()
        } else {
            Toast.makeText(requireContext(), "Izin kamera diperlukan", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTabCaptureBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnCapture.setOnClickListener {
            if (hasCameraPermission()) {
                openCamera()
            } else {
                permissionLauncher.launch(Manifest.permission.CAMERA)
            }
        }
    }

    private fun hasCameraPermission(): Boolean {
        return ContextCompat.checkSelfPermission(
            requireContext(),
            Manifest.permission.CAMERA
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun openCamera() {
        try {
            // Membuat alamat/URI tempat penyimpanan foto di Galeri
            currentPhotoUri = createGalleryPhotoUri()

            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE).apply {
                putExtra(MediaStore.EXTRA_OUTPUT, currentPhotoUri)
            }
            cameraLauncher.launch(intent)
        } catch (e: Exception) {
            Toast.makeText(requireContext(), "Gagal membuka kamera: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }

    private fun createGalleryPhotoUri(): Uri {
        val folderName = "NeSoulApps"
        val values = ContentValues().apply {
            put(MediaStore.Images.Media.DISPLAY_NAME, "IMG_${System.currentTimeMillis()}.jpg")
            put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
            put(MediaStore.Images.Media.RELATIVE_PATH, "Pictures/$folderName")
        }
        return requireContext().contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)
            ?: throw RuntimeException("Gagal membuat URI MediaStore")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
