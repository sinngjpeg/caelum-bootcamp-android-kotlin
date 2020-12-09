package com.example.twittelumapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.twittelumapp.activities.ListaTweetsActivity
import com.example.twittelumapp.modelo.Tweet

class TweetActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tweet)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_tweet, menu)
        return true

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item?.itemId) {
            R.id.tweet_menu_tweetar -> {
                publicaTweet()
                finish()
            }
            android.R.id.home -> finish()
        }

        return false
    }

    fun publicaTweet() {
        Log.i("tweet", "botao clicado")
        val campoDeMensagemDoTweet = findViewById<EditText>(R.id.tweet_mensagem)
        val mensagemDoTweet: String = campoDeMensagemDoTweet.text.toString()
        val tweet = Tweet(mensagemDoTweet)
        Toast.makeText(this, tweet.toString(), Toast.LENGTH_LONG).show()
        finish()

    }


}