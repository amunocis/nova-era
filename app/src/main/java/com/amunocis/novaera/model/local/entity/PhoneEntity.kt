package com.amunocis.novaera.model.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "phone_table")
data class PhoneEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val price: Int,
    val image: String
)
