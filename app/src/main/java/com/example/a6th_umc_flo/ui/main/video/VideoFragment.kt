package com.example.a6th_umc_flo.ui.main.video

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.a6th_umc_flo.databinding.FragmentVideoBinding

class VideoFragment : Fragment() {

    lateinit var binding: FragmentVideoBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentVideoBinding.inflate(inflater,container,false)

        return binding.root
    }
}