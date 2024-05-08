package com.example.eep523hw2.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.eep523hw2.databinding.FragmentNotificationsBinding
import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

class NotificationsFragment : Fragment() {

    private var _binding: FragmentNotificationsBinding? = null
    private val binding get() = _binding!!

    private var imageUri1: Uri? = null
    private var imageUri2: Uri? = null

    // Register the permissions callback, which handles the user's response to the
    // system permissions dialog.
    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            selectImage(currentImageSetter!!)
        } else {
            binding.textNotifications.text = "Permission denied to access photos."
        }
    }

    private var currentImageSetter: ((Uri) -> Unit)? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.btnSelectFirstPhoto.setOnClickListener {
            if (shouldShowRequestPermissionRationale(android.Manifest.permission.READ_EXTERNAL_STORAGE)) {
                requestPermissionLauncher.launch(android.Manifest.permission.READ_EXTERNAL_STORAGE)
            } else {
                selectImage { uri ->
                    imageUri1 = uri
                    binding.imageFirst.setImageURI(uri)
                }
            }
        }

        binding.btnSelectSecondPhoto.setOnClickListener {
            if (shouldShowRequestPermissionRationale(android.Manifest.permission.READ_EXTERNAL_STORAGE)) {
                requestPermissionLauncher.launch(android.Manifest.permission.READ_EXTERNAL_STORAGE)
            } else {
                selectImage { uri ->
                    imageUri2 = uri
                    binding.imageSecond.setImageURI(uri)
                }
            }
        }

        binding.btnSwapFaces.setOnClickListener {
            if (imageUri1 != null && imageUri2 != null) {
                swapFaces(imageUri1!!, imageUri2!!)
            } else {
                binding.textNotifications.text = "Please select both images first."
            }
        }

        return root
    }

    private fun selectImage(onImageSelected: (Uri) -> Unit) {
        currentImageSetter = onImageSelected
        if (shouldShowRequestPermissionRationale(android.Manifest.permission.READ_EXTERNAL_STORAGE)) {
            requestPermissionLauncher.launch(android.Manifest.permission.READ_EXTERNAL_STORAGE)
        } else {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            selectImageResultLauncher.launch(intent)
        }
    }

    private val selectImageResultLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            result.data?.data?.let { uri ->
                currentImageSetter?.invoke(uri)
            }
        }
    }

    private fun swapFaces(uri1: Uri, uri2: Uri) {
        lifecycleScope.launch {
            try {
                val resultBitmap = swapFacesMethod(uri1, uri2)
                activity?.runOnUiThread {
                    binding.imageFirst.setImageBitmap(resultBitmap)
                }
            } catch (e: Exception) {
                activity?.runOnUiThread {
                    binding.textNotifications.text = "Error swapping faces: ${e.message}"
                }
            }
        }
    }

    private fun swapFacesMethod(uri1: Uri, uri2: Uri): Bitmap {
        // Here you would integrate your face swapping library, like OpenCV
        throw NotImplementedError("Face swapping method is not implemented yet.")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
