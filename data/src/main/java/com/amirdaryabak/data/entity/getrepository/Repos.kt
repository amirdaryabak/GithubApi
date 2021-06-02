package com.amirdaryabak.data.entity.getrepository

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Repos(
    val name: String,
    @PrimaryKey
    val id: Int,
)