package com.example.downloadmanager

import android.app.DownloadManager
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.example.downloadmanager.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    lateinit var homeBinding: FragmentHomeBinding
    private val imageUrl = "https://images.unsplash.com/photo-1668863699009-1e3b4118675d?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=687&q=80"
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        homeBinding = FragmentHomeBinding.bind(view)
        return homeBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkWritePermissions()
        homeBinding.button.setOnClickListener {
            downloadImage(imageUrl, "imageBonito.jpg")
        }
    }

    private fun checkWritePermissions(){
        // First we need write permission in order to download file
        // check manifest.xml for permission
        if(ContextCompat.checkSelfPermission(requireContext(), android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            requestPermissions(arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE), 100)
        }
    }

    private fun downloadImage(imageUrl: String, fileName: String){
        val downloadManager = DownloadManager.Request(Uri.parse(imageUrl))
        downloadManager.setTitle(fileName)
        downloadManager.setDescription("Downloading $fileName")
        downloadManager.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
        downloadManager.setDestinationInExternalPublicDir( Environment.DIRECTORY_DOWNLOADS, fileName)
        val manager = requireActivity().getSystemService(DownloadManager::class.java)
        manager.enqueue(downloadManager)
    }
}