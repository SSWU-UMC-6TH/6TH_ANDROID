package com.example.flo

import HomeFragment
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.setFragmentResultListener
import com.example.flo.databinding.FragmentAlbumBinding
import com.google.android.material.tabs.TabLayoutMediator

private val information = arrayListOf("수록곡", "상세정보", "영상")

override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
): View? {
    ...
    val albumAdapter = AlbumVPAdapter(this)
    val binding = null
    binding.albumContentVp.adapter = albumAdapter

    TabLayoutMediator(binding.albumContentTb, binding.albumContentVp) { tab, position ->
        tab.text = information[position]
    }.attach()

    return binding.root
}