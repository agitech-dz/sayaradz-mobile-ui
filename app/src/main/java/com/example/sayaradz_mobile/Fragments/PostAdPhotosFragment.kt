package com.example.sayaradz_mobile.Fragments

import android.Manifest
import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil

import com.example.sayaradz_mobile.databinding.FragmentPostAdPhotosBinding
import kotlinx.android.synthetic.main.fragment_post_ad_photos.*
import android.provider.MediaStore
import android.content.Intent
import android.graphics.Bitmap
import android.app.Activity
import android.content.pm.PackageManager
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController


class PostAdPhotosFragment : Fragment() {

    private lateinit var binding: FragmentPostAdPhotosBinding
    private val CAMERA_REQUEST_CODE = 12345
    private val REQUEST_GALLERY_CAMERA = 54654

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            com.example.sayaradz_mobile.R.layout.fragment_post_ad_photos,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        takeAdPhoto.setOnClickListener {
            val permission = ContextCompat.checkSelfPermission(
                context!!,
                Manifest.permission.CAMERA
            )

            if (permission != PackageManager.PERMISSION_GRANTED) {
                Log.i("tag", "Permission to record denied")
                makeRequest()

            }
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            val frag = this

            frag.startActivityForResult(intent, CAMERA_REQUEST_CODE) // REQUEST_IMAGE_CAPTURE = 12345

        }

        takePhotoAction.setOnClickListener {
            val action = PostAdPhotosFragmentDirections.actionPostAdPhotosToPostAdDescriptionFragment()
            it.findNavController().navigate(action)

        }

    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == CAMERA_REQUEST_CODE) {

                val photo = data!!.extras!!.get("data") as Bitmap
                adTakenPhoto.setImageBitmap(photo)
                adTakenPhoto.visibility = View.VISIBLE
                takeAdPhoto.visibility = View.GONE

            }
        }
    }



    private fun makeRequest(){

            requestPermissions(arrayOf(Manifest.permission.CAMERA), CAMERA_REQUEST_CODE)

    }
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>, grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            CAMERA_REQUEST_CODE -> {

                if (grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED) {

                    Log.i("tag", "Permission has been denied by user")
                } else {
                    Log.i("tag", "Permission has been granted by user")
                }
            }
        }
    }


}
