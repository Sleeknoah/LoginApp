package com.example.loginapp.modules.profile.presentation

import android.Manifest
import android.app.Activity
import android.content.ContentResolver
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.example.loginapp.databinding.ActivityProfileBinding
import java.io.ByteArrayOutputStream

class ProfileActivity : AppCompatActivity() {
    private lateinit var profileBinding: ActivityProfileBinding
    private lateinit var getContent: ActivityResultLauncher<Intent>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        profileBinding = ActivityProfileBinding.inflate(layoutInflater)
        val view = profileBinding.root
        setContentView(view)

        val receivedData = intent.getSerializableExtra("dataMap") as HashMap<*, *>?
        if (receivedData != null) {
            Glide.with(this)
                .load(receivedData["image"])
                .into(profileBinding.profileImageView)

            profileBinding.emailTextView.text = receivedData["email"].toString()
            profileBinding.nameTextView.text = receivedData["fullName"].toString()
        }

        getContent = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val selectedImageUri = result.data?.data
                if (selectedImageUri != null) {
                    displaySelectedImage(selectedImageUri)
                }
            }
        }

        profileBinding.editProfileButton.setOnClickListener {
            Log.d("TAG", "onCreate: open")
            openGallery()
        }

    }

    private fun openGallery() {
        val permission = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            Manifest.permission.READ_MEDIA_IMAGES
        } else {
            Manifest.permission.READ_EXTERNAL_STORAGE
        }
        if (ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED) {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            getContent.launch(intent)
        } else {
            requestPermissions(arrayOf(permission), 0)
        }
    }

    private fun displaySelectedImage(uri: Uri) {
        val contentResolver: ContentResolver = contentResolver
        val bitmap: Bitmap = MediaStore.Images.Media.getBitmap(contentResolver, uri)
        val base64Image: String = encodeImageToBase64(bitmap)

        Glide.with(this)
            .load("data:image/png;base64,$base64Image")
            .into(profileBinding.profileImageView)
    }

    private fun encodeImageToBase64(bitmap: Bitmap): String {
        val byteArrayOutputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream)
        val byteArray = byteArrayOutputStream.toByteArray()
        return Base64.encodeToString(byteArray, Base64.DEFAULT)
    }
}