package com.example.a6th_umc_flo.ui.main.album

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.a6th_umc_flo.ui.main.detail.DetailFragment
import com.example.a6th_umc_flo.ui.song.SongFragment
import com.example.a6th_umc_flo.ui.main.video.VideoFragment

class AlbumVPAdapter(fragment : Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> SongFragment()
            1 -> DetailFragment()
            else -> VideoFragment()
        }
    }
}