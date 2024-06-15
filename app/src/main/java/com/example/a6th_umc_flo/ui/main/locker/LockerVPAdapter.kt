package com.example.a6th_umc_flo.ui.main.locker

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.a6th_umc_flo.ui.song.MusicFileFragment
import com.example.a6th_umc_flo.ui.song.SavedSongFragment
import com.example.a6th_umc_flo.ui.main.album.SavedAlbumFragment

class LockerVPAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> SavedSongFragment()
            1 -> MusicFileFragment()
            else -> SavedAlbumFragment()
        }
    }
}