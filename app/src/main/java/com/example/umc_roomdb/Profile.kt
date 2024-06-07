package com.example.umc_roomdb

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Profile(
    var name: String,
    var age: String,
    var phone: String
) {
    @PrimaryKey(autoGenerate = true) var id: Int = 0
}