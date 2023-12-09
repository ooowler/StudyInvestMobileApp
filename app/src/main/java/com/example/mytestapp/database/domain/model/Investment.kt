package com.example.mytestapp.database.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Investment(
    @PrimaryKey(autoGenerate = true)
    val uid: Long = 0,

    @ColumnInfo(name = "price")
    val price: Double,

    @ColumnInfo(name = "count")
    val count: Int,

    @ColumnInfo(name = "name")
    val name: String,
)