package com.example.downloadmanager

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.downloadmanager.databinding.FragmentAboutBinding

class AboutFragment : Fragment() {

    lateinit var aboutBinding: FragmentAboutBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_about, container, false)
        aboutBinding = FragmentAboutBinding.bind(view)
        return aboutBinding.root
    }

}