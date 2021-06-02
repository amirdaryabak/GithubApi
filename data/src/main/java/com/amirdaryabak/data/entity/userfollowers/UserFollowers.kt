package com.amirdaryabak.data.entity.userfollowers

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserFollowers(
    val avatar_url: String,
    @PrimaryKey
    val id: Int,
    val login: String,
)