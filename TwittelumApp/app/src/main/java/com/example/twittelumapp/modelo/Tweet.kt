package com.example.twittelumapp.modelo

import androidx.room.Database
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.RoomDatabase


@Entity
data class Tweet(
    val mensagem: String,
    val foto: String?,
    @PrimaryKey(autoGenerate = true) val id: Int = 0
) {
    override fun toString(): String {
        return mensagem
    }
}
