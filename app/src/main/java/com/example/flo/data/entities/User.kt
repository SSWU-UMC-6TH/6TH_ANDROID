package com.example.flo.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "UserTable")
data class User(
    // json으로 값을 내보내기 위해 @SerializedName
    @SerializedName(value = "email") var email: String,
    @SerializedName(value = "password") var password: String,
    @SerializedName(value = "name") var name: String
) {
    @PrimaryKey(autoGenerate = true) var id: Int = 0
}