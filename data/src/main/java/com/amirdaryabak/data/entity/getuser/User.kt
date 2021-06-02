package com.amirdaryabak.data.entity.getuser

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    val avatar_url: String,
    val bio: String,
    @PrimaryKey
    val login: String,
    val name: String,
)