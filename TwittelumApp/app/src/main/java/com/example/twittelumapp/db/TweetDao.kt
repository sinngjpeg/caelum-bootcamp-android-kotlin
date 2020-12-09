package com.example.twittelumapp.db

import android.content.Context
import androidx.room.*
import com.example.twittelumapp.modelo.Tweet

@Dao
interface TweetDao {
    @Insert
    fun salva(tweet: Tweet)

}

@Database(entities = [Tweet::class], version = 1)
abstract class TwittelumDatabase : RoomDatabase() {
    abstract fun tweetDao(): TweetDao

    companion object {
        private var database: TwittelumDatabase? = null
        private val DATABASE = "TwitterlumDB"

        fun getInstance(context: Context): TwittelumDatabase {

            return database ?: criaBanco(context).also { database = it }
        }


        private fun criaBanco(context: Context): TwittelumDatabase {
            return Room.databaseBuilder(context, TwittelumDatabase::class.java, DATABASE)
                .allowMainThreadQueries()
                .build()

        }
    }
}