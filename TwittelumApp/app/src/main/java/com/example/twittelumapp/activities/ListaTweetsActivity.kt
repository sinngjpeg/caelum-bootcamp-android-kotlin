package com.example.twittelumapp.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.twittelumapp.R
import com.example.twittelumapp.TweetActivity
import com.example.twittelumapp.databinding.ActivityListaTweetsBinding
import com.example.twittelumapp.db.TwittelumDatabase
import com.example.twittelumapp.modelo.Tweet
import com.google.android.material.snackbar.Snackbar

class ListaTweetsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListaTweetsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_tweets)

        binding = ActivityListaTweetsBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.fabNovo.setOnClickListener {
            val intencao = Intent(this, TweetActivity::class.java)
            startActivity(intencao)
        }
    }


    override fun onResume() {
        super.onResume()

        val tweetDao = TwittelumDatabase.getInstance(this).tweetDao()
        val tweets: List<Tweet> = tweetDao.lista()

        val adapter = ArrayAdapter<Tweet>(this, android.R.layout.simple_list_item_1, tweets)
        binding.listaTweet.adapter = adapter
    }
}