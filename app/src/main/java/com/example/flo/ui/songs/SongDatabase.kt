package com.example.flo.ui.songs

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.flo.data.entities.UserDao
import com.example.flo.data.entities.Album
import com.example.flo.data.entities.Like
import com.example.flo.data.entities.Song
import com.example.flo.data.entities.User
import com.example.flo.data.local.SongDao
import com.example.flo.ui.main.album.AlbumDao

@Database(entities = [Song::class, User::class, Like::class, Album::class], version = 1)
abstract class SongDatabase: RoomDatabase() {
    abstract fun songDao(): SongDao
    abstract fun userDao(): UserDao
    abstract fun albumDao(): AlbumDao

    companion object {
        private var instance: SongDatabase? = null

        @Synchronized
        fun getInstance(context: Context): SongDatabase? {
            if (instance == null) {
                synchronized(SongDatabase::class) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        SongDatabase::class.java,
                        "song_database"  // 다른 데이터베이스랑 이름이 중복되면 안 됨
                    ).allowMainThreadQueries().build()
                }
            }
            return instance
        }
    }
}