package com.example.a6th_umc_flo.ui.song

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.a6th_umc_flo.data.entities.Album
import com.example.a6th_umc_flo.data.entities.Like
import com.example.a6th_umc_flo.data.entities.Song
import com.example.a6th_umc_flo.data.entities.User
import com.example.a6th_umc_flo.data.entities.UserDao
import com.example.a6th_umc_flo.ui.main.album.AlbumDao

@Database(entities = [Song::class, User::class, Like::class, Album::class], version = 1)
abstract class SongDatabase: RoomDatabase() {
    abstract fun songDao(): SongDao
    abstract fun userDao(): UserDao
    abstract fun albumDao(): AlbumDao

    companion object{
        private var instance: SongDatabase?=null

        @Synchronized
        fun getInstance(context: Context): SongDatabase?{
            if (instance ==null)
            {
                synchronized(SongDatabase::class){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        SongDatabase::class.java,
                        "song-database"
                    ).allowMainThreadQueries().build()
                }
            }
            return instance
        }
    }
}