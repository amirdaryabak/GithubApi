package com.amirdaryabak.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Model(
    @PrimaryKey
    val id: Int,
)