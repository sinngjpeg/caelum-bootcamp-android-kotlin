package com.example.twittelumapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.twittelumapp.activities.ListaTweetsActivity

class TweetActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tweet)


        val botaoTwittar = findViewById<Button>(R.id.button_twittar)

        botaoTwittar.setOnClickListener(View.OnClickListener { publicaTweet() })

    }


    fun publicaTweet() {
        Log.i("tweet", "botao clicado")
        val campoDeMensagemDoTweet = findViewById<EditText>(R.id.tweet_mensagem)
        val mensagemDoTweet: String = campoDeMensagemDoTweet.text.toString()
        Toast.makeText(this, mensagemDoTweet, Toast.LENGTH_LONG).show()
        finish()

    }


}