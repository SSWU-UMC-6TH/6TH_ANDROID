package com.example.roomdbpractice

import androidx.room.*

//Data Access Object
@Dao
interface ProfileDao {
    @Insert
    fun insert(profile: Profile)

    @Update
    fun update(profile: Profile)

    @Delete
    fun delete(profile:Profile)

    @Query("SELECT * FROM Profile")
    fun getAll(): List<Profile>
}