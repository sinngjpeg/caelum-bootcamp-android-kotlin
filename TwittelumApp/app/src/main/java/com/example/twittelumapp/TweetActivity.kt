package com.example.twittelumapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.twittelumapp.activities.ListaTweetsActivity
import com.example.twittelumapp.db.TwittelumDatabase
import com.example.twittelumapp.modelo.Tweet
import com.example.twittelumapp.viewmodel.TweetViewModel
import com.example.twittelumapp.viewmodel.ViewModelFactory

class TweetActivity : AppCompatActivity() {

    private val viewModel: TweetViewModel by lazy {
        ViewModelProvider(this, ViewModelFactory).get(TweetViewModel::class.java)
    }

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

            R.id.menu_camera -> {
                val abrirCamera = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                startActivity(abrirCamera)

                true
            }
            android.R.id.home -> finish()
        }

        return false
    }

    fun publicaTweet() {
        val campoDeMensagemDoTweet = findViewById<EditText>(R.id.tweet_mensagem)
        val mensagemDoTweet: String = campoDeMensagemDoTweet.text.toString()
        val tweet = Tweet(mensagemDoTweet)
        val tweetDao = TwittelumDatabase.getInstance(this).tweetDao()
        viewModel.salva(tweet)
        Toast.makeText(this, "$tweet foi salvo com sucesso", Toast.LENGTH_LONG).show()
        //finish()

    }


}